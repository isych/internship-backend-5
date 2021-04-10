package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.service.CandidateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;


@RestController
@RequestMapping("/api/candidates/")
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
            return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
        }
        return (Objects.nonNull(candidateService.registerCandidate(candidateDto))) ?
                new ResponseEntity<>("Candidate created", HttpStatus.CREATED) :
                new ResponseEntity<>("Candidate not created", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
