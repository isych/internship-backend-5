package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.dto.resp.SearchCandidateDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.exception.ApiRequestExceptionDto;
import com.exadel.backendservice.exception.ApiResponseExceptionDto;
import com.exadel.backendservice.service.CandidateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/candidates")
@Api(tags = "Контроллер для работы с кандидатами")
public class CandidateController {
    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    /**
     * Метод регистрации нового кандидата
     *
     * @param candidateDto - объект, передаваемый из формы регистраци кандидатов
     */
    @ApiOperation(value = "Метод для регистрации нового пользователя ")
    @PostMapping
    public ResponseEntity<String> registerCandidate(@RequestBody @Valid RegisterCandidateDto candidateDto) {

        if (Objects.isNull(candidateDto)) {
            throw new ApiRequestExceptionDto("Dto level exception (Candidate)");
        }

        Candidate candidateWithId = candidateService.registerCandidate(candidateDto);
        if (Objects.isNull(candidateWithId)) {
            throw new ApiResponseExceptionDto("Entity Level Exception (Candidate)");
        }

        return new ResponseEntity<>("Candidate was created", HttpStatus.CREATED);
    }

    @ApiOperation(value = "Метод для частичного извлечения кандидатов")
    @GetMapping
    public ResponseEntity<Page<SearchCandidateDto>> getCandidatePage(Pageable pageable) {
        return new ResponseEntity<>(candidateService.getPageOfCandidates(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Метод для получения всех статусов кандидата")
    @GetMapping("/statuses")
    public ResponseEntity<List<String>> getStatuses() {
        return new ResponseEntity<>(candidateService.getAllStatuses(), HttpStatus.OK);
    }

    @ApiOperation(value = "Метод для получения всех статусов интервью кандидата")
    @GetMapping("/interview-statuses")
    public ResponseEntity<List<String>> getInterviewStatuses() {
        return new ResponseEntity<>(candidateService.getInterviewStatuses(), HttpStatus.OK);
    }

    @ApiOperation(value = "Метод для получения всех значений предпочитаемого времени кандидата ")
    @GetMapping("/preferred-times")
    public ResponseEntity<List<String>> getPreferredTime() {
        return new ResponseEntity<>(candidateService.getAllPreferredTime(), HttpStatus.OK);
    }
}
