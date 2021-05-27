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
import com.exadel.backendservice.mapper.candidate.CandidateResponseMapper;
import com.exadel.backendservice.mapper.candidate.DetailedCandidateMapper;
import com.exadel.backendservice.mapper.candidate.RegisterCandidateMapper;
import com.exadel.backendservice.mapper.candidate.SearchCandidateMapper;
import com.exadel.backendservice.model.*;
import com.exadel.backendservice.repository.*;
import com.exadel.backendservice.service.CandidateService;
import com.exadel.backendservice.service.utils.FeedbackLinkGenerator;
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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
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
    private final CityRepositoryJPA cityRepositoryJPA;
    private final CandidateRepositoryJPA candidateRepositoryJPA;
    private final RegisterCandidateMapper registerCandidateMapper;
    private final SearchCandidateMapper searchCandidateMapper;
    private final DetailedCandidateMapper detailedCandidateMapper;
    private final CandidateResponseMapper candidateMapper;
    private final FileStore fileStoreService;
    private final FeedbackLinkGenerator feedbackLinkGenerator;


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
        try {
            mailSender.send(
                    savedCandidate.getEmail(),
                    MessageSubject.REGISTRATION.getSubject(),
                    new MessageBody(
                            savedCandidate.getFullName(),
                            Arrays.asList(MessageBodyBase.CANDIDATE_REGISTERED)
                    ).getBody()
            );
        } catch (MailException ex) {
            throw new ApiResponseException("Internal error: mail can't be send to candidate");
        }
        return candidateRespDto;
    }

    @Override
    public Page<SearchCandidateDto> getPageOfCandidates(Pageable pageable) {
        Page<Candidate> page = candidateRepository.findAll(pageable);
        List<SearchCandidateDto> candidateDtoList = page.get().map(searchCandidateMapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(candidateDtoList);
    }

    @Override
    public DetailedCandidateDto getDetailedCandidateDto(UUID id) {
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
    public CandidateRespDto uploadCv(UUID id, MultipartFile file) {
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
    public byte[] downloadCv(UUID id) {
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
    public Boolean hasCv(UUID id) {
        Optional<Candidate> candidateOptional = candidateRepository.findById(id);
        if (candidateOptional.isPresent()) {
            return hasCv(candidateOptional.get());
        }
        throw new DBNotFoundException(UNABLE_TO_FIND_CANDIDATE);
    }

    @Override
    public String getCvName(UUID id) {
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

    @Override
    public CandidateRespDto updateStatus(UUID id, CandidateStatus status) {
        Candidate candidate;
        Optional<Candidate> candidateOptional = candidateRepository.findById(id);
        if (candidateOptional.isPresent()) {
            candidate = candidateOptional.get();
            candidate.setStatus(status);
            candidateRepository.save(candidate);

            List<MessageBodyBase> messageBodyBases = new ArrayList<>();
            if (status.equals(CandidateStatus.GREEN)) {
                messageBodyBases.add(MessageBodyBase.CANDIDATE_ACCEPTED);
            } else if (status.equals(CandidateStatus.RED)) {
                messageBodyBases.add(MessageBodyBase.CANDIDATE_REJECTED);
            }

            try {
                mailSender.send(
                        candidate.getEmail(),
                        MessageSubject.RESULT.getSubject(),
                        new MessageBody(candidate.getFullName(), messageBodyBases).getBody()
                );

            } catch (MailException ex) {
                throw new ApiResponseException("Internal error: mail can't be send to candidate");
            }
            return candidateMapper.toDto(candidate);
        }
        throw new DBNotFoundException("Candidate with this id does not found");
    }

    @Override
    public CandidateRespDto updateInterviewStatus(UUID id, InterviewProcess interviewProcess, HttpServletRequest request) {
        Candidate candidate;
        Optional<Candidate> candidateOptional = candidateRepository.findById(id);
        if (candidateOptional.isPresent()) {
            candidate = candidateOptional.get();
            candidate.setInterviewProcess(interviewProcess);
            candidateRepository.save(candidate);
            feedbackLinkGenerator.sendMessageWithLinkForFeedback(candidate, interviewProcess, request);
            return candidateMapper.toDto(candidate);
        }
        throw new DBNotFoundException("Candidate with this id does not found");
    }

    @Override
    public Page<SearchCandidateDto> getCandidatesWithFilter(List<String> primaryTech, List<String> interviewProccess, List<String> status, List<String> country, List<String> event, Pageable pageable) {
        final int start = (int) pageable.getOffset();
        Map<String, List<String>> map = new HashMap<>();
        paramsToMap(primaryTech, interviewProccess, status, country, event, map);
        if (map.size() != 0) {
            String param = createPartQuery(map);
            String query = "select id from candidate where (" + param.replaceAll(" and", ") and").replaceAll("and ", "and (") + ")";
            List<UUID> list = candidateRepositoryJPA.findAllByFilter(query);
            List candidates = getSearchCandidateDtos(list);
            if (candidates != null) {
                int end = Math.min((start + pageable.getPageSize()), candidates.size());
                return new PageImpl<>(candidates.subList(start, end), pageable, candidates.size());
            } else {
                return new PageImpl<>(new ArrayList<>(), pageable, 0);
            }
        } else {
            List candidates = candidateRepository.findAll().stream()
                    .map(searchCandidateMapper::toDto)
                    .collect(Collectors.toList());
            int end = Math.min((start + pageable.getPageSize()), candidates.size());
            return new PageImpl<>(candidates.subList(start, end), pageable, candidates.size());
        }

    }

    @Override
    public Map<String, Object> getInfoForFilter() {
        List<Candidate> candidates = candidateRepository.findAll();
        Map<String, Object> info = new HashMap<>();
        Set<String> country = candidates.stream().map(elem -> elem.getCity().getCountry().getName()).collect(Collectors.toSet());
        Set<String> tech = candidates.stream().map(elem -> elem.getPrimaryTech().getName()).collect(Collectors.toSet());
        Set<String> event = candidates.stream().map(elem -> elem.getEvent().getName()).collect(Collectors.toSet());
        Set<CandidateStatus> status = candidates.stream().map(elem -> elem.getStatus()).collect(Collectors.toSet());
        Set<InterviewProcess> interviewProccess = candidates.stream().map(elem -> elem.getInterviewProcess()).collect(Collectors.toSet());
        info.put("countryName", country);
        info.put("primaryTech", tech);
        info.put("eventName", event);
        info.put("status", status);
        info.put("interviewProccess", interviewProccess);
        return info;
    }

    @Override
    public CandidateRespDto editCandidate(UUID id, RegisterCandidateDto registerCandidateDto) {
        Optional<Candidate> candidateFromDb = candidateRepository.findById(id);
        if (candidateFromDb.isPresent()) {
            Candidate candidate = registerCandidateMapper.toEntity(registerCandidateDto);

            candidate.setId(id);
            candidate.setCv(candidateFromDb.get().getCv());
            candidate.setCvPath(candidateFromDb.get().getCvPath());

            Candidate savedCandidate = candidateRepository.save(candidate);
            return candidateMapper.toDto(savedCandidate);
        }
        throw new DBNotFoundException("Unable to find candidate by id");
    }

    private List<SearchCandidateDto> getSearchCandidateDtos(List<UUID> list) {
        List<Candidate> candidateList = list.stream()
                .map(elem -> candidateRepository.findById(elem).get())
                .collect(Collectors.toList());
        if (!candidateList.isEmpty()) {
            return candidateList.stream()
                    .map(searchCandidateMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    private String createPartQuery(Map<String, List<String>> map) {
        StringBuilder sb = new StringBuilder();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String key = (String) pair.getKey();
            List<String> elems = (List<String>) pair.getValue();
            for (String el : elems) {
                sb.append(key).append(" = '").append(el).append("' or ");
            }
            String str = sb.toString();
            String res = str.substring(0, str.length() - 3) + "and ";
            sb.delete(0, sb.length());
            sb.append(res);

        }
        String str = sb.toString();
        return str.substring(0, str.length() - 5);
    }

    private void paramsToMap(List<String> primaryTech, List<String> interviewProccess, List<String> status, List<String> country, List<String> event, Map<String, List<String>> map) {
        if (primaryTech != null && !primaryTech.isEmpty()) {
            List<String> list = new ArrayList<>();
            for (String elem : primaryTech) {
                UUID techId = techRepository.findByName(elem).get().getId();
                list.add(techId.toString());
            }
            map.put("tech_id", list);
        }
        if (interviewProccess != null && !interviewProccess.isEmpty()) {
            map.put("interview_process", interviewProccess);
        }
        if (status != null && !status.isEmpty()) {
            map.put("status", status);
        }
        if (country != null && !country.isEmpty()) {
            List<UUID> cityId = new ArrayList<>();
            for (String elem : country) {
                cityId = cityRepositoryJPA.findCitiesByCountryName(elem);
            }
            map.put("city_id", cityId.stream().map(UUID::toString).collect(Collectors.toList()));
        }
        if (event != null && !event.isEmpty()) {
            List<String> eventNames = event.stream().map(elem -> eventRepository.findByName(elem).get().getId().toString()).collect(Collectors.toList());
            map.put("event_id", eventNames);
        }
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
