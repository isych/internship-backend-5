package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.exception.ApiRequestException;
import com.exadel.backendservice.exception.ApiResponseException;
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
    public ResponseEntity<String> registerCandidate(@RequestBody @Valid RegisterCandidateDto candidateDto){
        if (Objects.isNull(candidateDto)) {
            throw new ApiRequestException("Недостаточно данных для регистрации кандидата");
        }

        if(Objects.isNull(candidateService.registerCandidate(candidateDto))){
            throw new ApiResponseException("Кандидат не зарегистрирован в системе");
        }

        return  new ResponseEntity<>("Регистрация кандидата прошла успешно", HttpStatus.CREATED);
    }
}
