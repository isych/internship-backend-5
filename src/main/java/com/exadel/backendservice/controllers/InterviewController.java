package com.exadel.backendservice.controllers;

import com.exadel.backendservice.service.InterviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor
@Api(tags = "Контроллер для работы с интервью")
public class InterviewController {
    InterviewService interviewService;

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
