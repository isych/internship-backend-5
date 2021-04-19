package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.dto.resp.CandidateWithIdDto;
import com.exadel.backendservice.dto.resp.DetailedCandidateDto;
import com.exadel.backendservice.dto.resp.SearchCandidateDto;
import com.exadel.backendservice.exception.ApiRequestException;
import com.exadel.backendservice.exception.ApiResponseException;
import com.exadel.backendservice.service.CandidateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
@Api(tags = "Контроллер для работы с кандидатами")
public class CandidateController {
    private final CandidateService candidateService;

    /**
     * Метод регистрации нового кандидата
     *
     * @param registerCandidateDto - объект, передаваемый из формы регистраци кандидатов
     */
    @ApiOperation(value = "Метод для регистрации нового кандидата")
    @PostMapping
    public ResponseEntity<CandidateWithIdDto> registerCandidate(@RequestBody @Valid RegisterCandidateDto registerCandidateDto) {
        CandidateWithIdDto candidateWithIdDto;
        if (Objects.isNull(registerCandidateDto)) {
            throw new ApiRequestException("Dto level exception (Candidate)");
        }
        candidateWithIdDto = candidateService.registerCandidate(registerCandidateDto);
        if (Objects.isNull(candidateWithIdDto)) {
            throw new ApiResponseException("Entity Level Exception (Candidate)");
        }
        return new ResponseEntity<>(candidateWithIdDto, HttpStatus.CREATED);
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
    public ResponseEntity<DetailedCandidateDto> getDetailedCandidateDtoBy(@RequestParam(name = "id") Integer id) {
        return new ResponseEntity<>(candidateService.getDetailedCandidateDto(id), HttpStatus.OK);
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

    @ApiOperation(value = "Метод для загрузки резюме на сервер. Принимает doc, docx, pdf")
    @PostMapping("/{id}/cv/upload")
    public ResponseEntity<CandidateWithIdDto> uploadCv(@PathVariable("id") Integer id, @RequestPart(value = "file") MultipartFile multipartFile) {
        Optional<CandidateWithIdDto> candidateWithIdDtoOptional = candidateService.uploadCv(id, multipartFile);
        if (candidateWithIdDtoOptional.isPresent()) {
            return new ResponseEntity<>(candidateWithIdDtoOptional.get(), HttpStatus.OK);
        }
        throw new ApiResponseException("Internal error");
    }

    @ApiOperation(value = "Метод для скачивания резюме")
    @GetMapping("/{id}/cv/download")
    public ResponseEntity<ByteArrayResource> downloadCv(@PathVariable("id") Integer id) throws IOException {
        byte[] data = candidateService.downloadCv(id);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + "newFile" + "\"")
                .body(resource);
    }

}
