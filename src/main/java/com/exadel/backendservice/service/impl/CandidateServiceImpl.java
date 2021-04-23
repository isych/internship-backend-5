package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.dto.resp.CandidateRespDto;
import com.exadel.backendservice.dto.resp.DetailedCandidateDto;
import com.exadel.backendservice.dto.resp.SearchCandidateDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.exception.ApiResponseException;
import com.exadel.backendservice.exception.CannotUploadFileException;
import com.exadel.backendservice.exception.DBNotFoundException;
import com.exadel.backendservice.exception.UnsupportedMediaFormatException;
import com.exadel.backendservice.mapper.converter.CandidateResponseMapper;
import com.exadel.backendservice.mapper.converter.DetailedCandidateMapper;
import com.exadel.backendservice.mapper.converter.RegisterCandidateMapper;
import com.exadel.backendservice.mapper.converter.SearchCandidateMapper;
import com.exadel.backendservice.model.*;
import com.exadel.backendservice.repository.CandidateRepository;
import com.exadel.backendservice.repository.CityRepository;
import com.exadel.backendservice.repository.EventRepository;
import com.exadel.backendservice.repository.TechRepository;
import com.exadel.backendservice.service.CandidateService;
import com.exadel.backendservice.service.utils.FileStore;
import com.exadel.backendservice.service.utils.MailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final String UNABLE_TO_FIND_CANDIDATE = "Unable to find candidate";
    public static final String MORE_THAN_ONE_CHAR_REGEX = "( )+";

    private final MailSender mailSender;
    private final CandidateRepository candidateRepository;
    private final CityRepository cityRepository;
    private final TechRepository techRepository;
    private final EventRepository eventRepository;

    private final RegisterCandidateMapper registerCandidateMapper;
    private final SearchCandidateMapper searchCandidateMapper;
    private final DetailedCandidateMapper detailedCandidateMapper;
    private final CandidateResponseMapper candidateMapper;

    private final FileStore fileStoreService;

    @Override
    public CandidateRespDto registerCandidate(RegisterCandidateDto dto) {
        if (!eventRepository.existsByName(dto.getEvent())) {
            throw new DBNotFoundException("Event with this name does not exist");
        }
        if (!cityRepository.existsByName(dto.getCity())) {
            throw new DBNotFoundException("City with this name does not exists");
        }
        if (!techRepository.existsByName(dto.getPrimaryTech())) {
            throw new DBNotFoundException("Tech with this name does not exists");
        }
        log.debug("CandidateDto ready to convert: {}", dto);
        Candidate candidate = registerCandidateMapper.toEntity(dto);
        Candidate savedCandidate = candidateRepository.save(candidate);
        CandidateRespDto candidateRespDto = candidateMapper.toDto(savedCandidate);
        log.debug("Candidate with ID: {}", candidateRespDto.getId());
        sendMailToCandidate(candidateRespDto.getEmail(), candidateRespDto.getFullName());
        return candidateRespDto;
    }

    @Override
    public Page<SearchCandidateDto> getPageOfCandidates(Pageable pageable) {
        Page<Candidate> page = candidateRepository.findAll(pageable);
        List<SearchCandidateDto> candidateDtoList = page.get().map(searchCandidateMapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(candidateDtoList);
    }

    @Override
    public DetailedCandidateDto getDetailedCandidateDto(Integer id) {
        Optional<Candidate> candidateOptional = candidateRepository.findById(id);
        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            return detailedCandidateMapper.toDto(candidate);
        }
        throw new DBNotFoundException(UNABLE_TO_FIND_CANDIDATE);
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
        return Stream.of(PreferredCandidateTime.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public boolean hasCv(Candidate candidate) {
        if (Objects.isNull(candidate.getCvPath()) || Objects.isNull(candidate.getCv())) {
            return false;
        }
        return !candidate.getCvPath().isEmpty() && !candidate.getCv().isEmpty();
    }

    @Override
    public CandidateRespDto uploadCv(Integer id, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        if (!MimeTypes.isCvFile(Objects.requireNonNull(file.getContentType()))) {
            throw new UnsupportedMediaFormatException("FIle uploaded is not doc or pdf");
        }
        Optional<Candidate> candidateOptional = candidateRepository.findById(id);
        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();

            String path = String.format("%s/%s", BucketName.CV.getBucketName(), UUID.randomUUID());
            String fileName = buildCvName(candidate);
            try {
                fileStoreService.upload(path, fileName, Optional.of(fileStoreService.addMetadata(file)), file.getInputStream());
            } catch (IOException e) {
                throw new CannotUploadFileException("Failed to upload file", e);
            }
            candidate.setCv(fileName);
            candidate.setCvPath(path);
            candidateRepository.save(candidate);
            return candidateMapper.toDto(candidate);
        } else {
            throw new DBNotFoundException(UNABLE_TO_FIND_CANDIDATE);
        }
    }

    @Override
    public byte[] downloadCv(Integer id) {
        Optional<Candidate> candidateOptional = candidateRepository.findById(id);
        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            if (hasCv(candidate)) {
                return fileStoreService.download(candidate.getCvPath(), candidate.getCv());
            }
            throw new DBNotFoundException("Candidate does not have an cv");
        } else {
            throw new DBNotFoundException(UNABLE_TO_FIND_CANDIDATE);
        }
    }

    @Override
    public Boolean hasCv(Integer id) {
        Optional<Candidate> candidateOptional = candidateRepository.findById(id);
        if (candidateOptional.isPresent()) {
            return hasCv(candidateOptional.get());
        }
        throw new DBNotFoundException(UNABLE_TO_FIND_CANDIDATE);
    }

    @Override
    public String getCvName(Integer id) {
        Optional<Candidate> candidateOptional = candidateRepository.findById(id);
        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            if (hasCv(candidate)) {
                return candidate.getCv();
            }
            throw new DBNotFoundException("Candidate don't have cv");
        }
        throw new DBNotFoundException(UNABLE_TO_FIND_CANDIDATE);
    }

    private String buildCvName(Candidate candidate) {
        String result = candidate.getPrimaryTech().getName() + "_" +
                candidate.getFullName() + "_" +
                candidate.getEvent().getName();
        return result.replaceAll(MORE_THAN_ONE_CHAR_REGEX, "_");
    }

    private void sendMailToCandidate(String email, String name) {
        String message = String.format("%s,\nyour registration completed successfully!\nWait for the recruiter's call soon.", name);
        try {
            mailSender.send(email, "Registration for Exadel event", message);
        } catch (MailException ex) {
            throw new ApiResponseException("Internal error: mail can't be send to candidate");
        }
    }

}
