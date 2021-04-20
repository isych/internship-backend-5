package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.dto.resp.CandidateRespDto;
import com.exadel.backendservice.dto.resp.DetailedCandidateDto;
import com.exadel.backendservice.dto.resp.SearchCandidateDto;
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

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
@Api(tags = "Контроллер для работы с кандидатами")
public class CandidateController {
    private final CandidateService candidateService;

    /**
     * Метод регистрации нового кандидата
     *
     * @param registerCandidateDto - объект, передаваемый из формы регистрации кандидатов
     */
    @ApiOperation(value = "Метод для регистрации нового кандидата на событие")
    @PostMapping
    public ResponseEntity<?> registerCandidate(@RequestBody @Valid RegisterCandidateDto registerCandidateDto) {
        CandidateRespDto candidateRespDto;
        if (Objects.isNull(registerCandidateDto)) {
            return new ResponseEntity<>("Cannot create candidate. Invalid input data.", HttpStatus.OK);
        }
        candidateRespDto = candidateService.registerCandidate(registerCandidateDto);
        if (Objects.isNull(candidateRespDto)) {
            return new ResponseEntity<>("Cannot create candidate. Internal error.", HttpStatus.OK);
        }
        return new ResponseEntity<>(candidateRespDto, HttpStatus.CREATED);
    }

    /**
     * Метод для частичного извлечения кандидатов
     *
     * @param pageable - объект, хранящий информацию о параметрах (page, size, name)
     */
    @ApiOperation(value = "Метод для постраничного предоставления кандидатов")
    @GetMapping
    public ResponseEntity<Page<SearchCandidateDto>> getCandidatePage(Pageable pageable) {
        return new ResponseEntity<>(candidateService.getPageOfCandidates(pageable), HttpStatus.OK);
    }

    /**
     * Метод извлечения кандидата по id
     *
     * @param id - объект, передаваемый из формы юзера для поиска кандидата по id
     */
    @ApiOperation(value = "Метод для получения подробной информации о кандидате по id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<DetailedCandidateDto> getDetailedCandidateDtoBy(@RequestParam(name = "id") Integer id) {
        return new ResponseEntity<>(candidateService.getDetailedCandidateDto(id), HttpStatus.OK);
    }

    /**
     * Метод для получения всех статусов кандидата
     */
    @ApiOperation(value = "Метод для получения всех статусов кандидата(GREEN|YELLOW|RED)")
    @GetMapping("/statuses")
    public ResponseEntity<List<String>> getStatuses() {
        return new ResponseEntity<>(candidateService.getAllStatuses(), HttpStatus.OK);
    }

    /**
     * Метод для получения всех статусов интервью кандидата
     */
    @ApiOperation(value = "Метод для получения всех статусов интервью кандидата(Ожидает звонка|Прошел первое интервью|Прошел второе)")
    @GetMapping("/interview-statuses")
    public ResponseEntity<List<String>> getInterviewStatuses() {
        return new ResponseEntity<>(candidateService.getInterviewStatuses(), HttpStatus.OK);
    }

    /**
     * Метод для получения всех значений предпочитаемого времени кандидата
     */
    @ApiOperation(value = "Метод для получения всех значений предпочитаемого времени для собеседования кандидата(10-12, 12-14, 14-16, 16-18 часов)")
    @GetMapping("/preferred-times")
    public ResponseEntity<List<String>> getPreferredTime() {
        return new ResponseEntity<>(candidateService.getAllPreferredTime(), HttpStatus.OK);
    }

    /**
     * Метод для загрузки резюме на сервер. Принимает doc, docx, pdf
     *
     * @param id, multipartFile -
     */
    @ApiOperation(value = "Метод для загрузки резюме в систему. Принимает doc, docx, pdf. Принимает id кандидата которое возвращается после создания кандидата")
    @PostMapping("/{id}/cv/upload")
    public ResponseEntity<CandidateRespDto> uploadCv(@PathVariable("id") Integer id, @RequestPart(value = "file") MultipartFile multipartFile) {
        return new ResponseEntity<>(candidateService.uploadCv(id, multipartFile), HttpStatus.OK);
    }

    /**
     * Метод для скачивания резюме
     *
     * @param id -
     */
    @ApiOperation(value = "Метод для скачивания резюме кандидата")
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

    @ApiOperation(value = "Метод для проверки прикрепил ли кандидат резюме")
    @GetMapping("/{id}/cv/exists")
    public ResponseEntity<Boolean> checkCv(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(candidateService.hasCv(id), HttpStatus.OK);
    }

}
