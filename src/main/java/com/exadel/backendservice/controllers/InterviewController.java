package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.req.CreateInterviewDto;
import com.exadel.backendservice.dto.resp.InterviewRespDto;
import com.exadel.backendservice.exception.ApiRequestException;
import com.exadel.backendservice.exception.ApiResponseException;
import com.exadel.backendservice.service.InterviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interviews/")
@Api(tags = "Контроллер для работы с интервью")
public class InterviewController {

    private final InterviewService interviewService;

    @ApiOperation(value = "Метод для создания интервью")
    @PostMapping("/create")
    public ResponseEntity<?> createInterview(@RequestBody CreateInterviewDto createInterviewDto) {
        if(Objects.isNull(createInterviewDto)) {
            throw new ApiRequestException("Interview is null");
        }
        InterviewRespDto interviewRespDto = interviewService.saveInterview(createInterviewDto);
        if (Objects.isNull(interviewRespDto)) {
            throw new ApiResponseException("Cannot create interview. Internal error.");
        }
        return new ResponseEntity<>(interviewRespDto , HttpStatus.CREATED);
    }

    @ApiOperation(value = "Метод для изменения интервьюера для интервью")
    @PutMapping("/{interviewId}/employee/{employeeId}")
    public ResponseEntity<?> changeInterviewer(@PathVariable("interviewId") Integer interviewId,
                                               @PathVariable("employeeId") Integer employeeId) {
        return new ResponseEntity<>(interviewService.updateInterviewer(interviewId, employeeId), HttpStatus.OK);
    }

    /**
     * Метод сохранения нового фидбэка
     *
     * @param id - идентификационный номер интервью, для которого необходимо сохранить новый фидбэк по резульиатам этого интервью
     * @param feedback - строка для хранения фидбэка
     */
    @ApiOperation(value = "Метод сохранения нового фидбэка")
    @PutMapping(value = "/{id}/feedbacks/save")
    public ResponseEntity<?> setFeedback(@PathVariable("id") Integer id, String feedback) {
        return new ResponseEntity<>(interviewService.saveFeedback(id, feedback) , HttpStatus.OK);
    }

}
