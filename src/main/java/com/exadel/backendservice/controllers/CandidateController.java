package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.dto.resp.CandidateWithIdDto;
import com.exadel.backendservice.dto.resp.DetailedCandidateDto;
import com.exadel.backendservice.dto.resp.SearchCandidateDto;
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
     * @param registerCandidateDto - объект, передаваемый из формы регистраци кандидатов
     */
    @ApiOperation(value = "Метод для регистрации нового пользователя")
    @PostMapping
    public ResponseEntity<CandidateWithIdDto> registerCandidate(@RequestBody @Valid RegisterCandidateDto registerCandidateDto){
        CandidateWithIdDto candidateWithIdDto;
        if (Objects.isNull(registerCandidateDto)) {
            throw new ApiRequestExceptionDto("Dto level exception (Candidate)");
        }
        candidateWithIdDto = candidateService.registerCandidate(registerCandidateDto);
        if(Objects.isNull(candidateWithIdDto)){
            throw new ApiResponseExceptionDto("Entity Level Exception (Candidate)");
        }
        return  new ResponseEntity(candidateWithIdDto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Метод для частичного извлечения кандидатов")
    @GetMapping
    public ResponseEntity<Page<SearchCandidateDto>> getCandidatePage(Pageable pageable) {
        return new ResponseEntity<>(candidateService.getPageOfCandidates(pageable), HttpStatus.OK);
    }

    /**
     * Метод регистрации нового кандидата
     *
     * @param id - объект, передаваемый из формы юзера для поиска кандидата по id
     */
    @ApiOperation(value = "Метод для извлечения кандидата по id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<DetailedCandidateDto> getDetailedCandidateDtoBy(@RequestParam(name = "id") Integer id){
        return new ResponseEntity(candidateService.getDetailedCandidateDto(id), HttpStatus.OK);
    }
}
