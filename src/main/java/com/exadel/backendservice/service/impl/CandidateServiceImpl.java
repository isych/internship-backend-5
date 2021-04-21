package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.dto.resp.CandidateWithIdDto;
import com.exadel.backendservice.dto.resp.DetailedCandidateDto;
import com.exadel.backendservice.dto.resp.SearchCandidateDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.exception.ApiResponseException;
import com.exadel.backendservice.mapper.converter.CandidateWithIdMapper;
import com.exadel.backendservice.mapper.converter.DetailedCandidateMapper;
import com.exadel.backendservice.mapper.converter.RegisterCandidateMapper;
import com.exadel.backendservice.mapper.converter.SearchCandidateMapper;
import com.exadel.backendservice.model.*;
import com.exadel.backendservice.repository.CandidateRepository;
import com.exadel.backendservice.service.CandidateService;
import com.exadel.backendservice.service.utils.MailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private MailSender mailSender;

    private final CandidateRepository candidateRepository;
    private final RegisterCandidateMapper registerCandidateMapper;
    private final SearchCandidateMapper searchCandidateMapper;
    private final DetailedCandidateMapper detailedCandidateMapper;
    private final CandidateWithIdMapper candidateMapper;
    private final FileStoreServiceImpl fileStoreService;

    @Override
    public CandidateWithIdDto registerCandidate(RegisterCandidateDto registerCandidateDto) {
        log.debug("CandidateDto ready to convert: {}", registerCandidateDto);
        Candidate candidate = registerCandidateMapper.toEntity(registerCandidateDto);
        Candidate savedCandidate = candidateRepository.save(candidate);
        CandidateWithIdDto candidateWithIdDto = candidateMapper.toDto(savedCandidate);
        log.debug("Candidate with ID: {}", candidateWithIdDto.getId());
        if(candidateWithIdDto.getEmail()!=null){
            String message = String.format("%s,\nyour registration completed successfully!\nWait for the recruiter's call soon.", candidateWithIdDto.getFullName());
           try {
               mailSender.send(candidateWithIdDto.getEmail(), "Registration for Exadel event", message);
           }catch (MailException ex){
               throw new ApiResponseException("Internal error: mail can't be send to candidate");
           }

        }
        return candidateWithIdDto;
    }

    @Override
    public Page<SearchCandidateDto> getPageOfCandidates(Pageable pageable) {
        Page<Candidate> page = candidateRepository.findAll(pageable);
        List<SearchCandidateDto> candidateDtoList = page.get().map(searchCandidateMapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(candidateDtoList);
    }

    @Override
    public DetailedCandidateDto getDetailedCandidateDto(Integer id) {
        Candidate candidate = candidateRepository.getOne(id);
        return detailedCandidateMapper.toDto(candidate);
    }

    @Override
    public List<String> getAllStatuses() {
        return Stream.of(CandidateStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getInterviewStatuses() {
        return Stream.of(InterviewProcess.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllPreferredTime() {
        return Stream.of(PreferredTime.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CandidateWithIdDto> uploadCv(Integer id, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        if (!MimeTypes.isCvFile(Objects.requireNonNull(file.getContentType()))) {
            throw new IllegalStateException("FIle uploaded is not doc or pdf");
        }
        Optional<Candidate> candidateOptional = candidateRepository.findById(id);
        if (candidateOptional.isPresent()) {
            String path = String.format("%s/%s", BucketName.CV.getBucketName(), UUID.randomUUID());
            String fileName = String.format("%s", file.getOriginalFilename());
            try {
                fileStoreService.upload(path, fileName, Optional.of(fileStoreService.addMetadata(file)), file.getInputStream());
            } catch (IOException e) {
                throw new IllegalStateException("Failed to upload file", e);
            }
            Candidate candidate = candidateOptional.get();
            candidate.setCv(fileName);
            candidate.setCvPath(path);
            candidateRepository.save(candidate);
            return Optional.of(candidateMapper.toDto(candidate));
        }
        return Optional.empty();
    }

    @Override
    public byte[] downloadCv(Integer id) {
        Optional<Candidate> candidateOptional = candidateRepository.findById(id);
        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            return fileStoreService.download(candidate.getCvPath(), candidate.getCv());
        }
        return new byte[0];
    }
}
