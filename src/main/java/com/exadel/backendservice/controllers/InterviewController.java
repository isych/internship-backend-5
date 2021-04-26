package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.req.CreateInterviewDto;
import com.exadel.backendservice.exception.ApiRequestException;
import com.exadel.backendservice.exception.ApiResponseException;
import com.exadel.backendservice.service.InterviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        System.out.println(createInterviewDto);
        if(Objects.isNull(createInterviewDto)) {
            throw new ApiRequestException("Interview is null");
        }
        CreateInterviewDto interviewDto = interviewService.saveInterview(createInterviewDto);
        if (Objects.isNull(interviewDto)) {
            throw new ApiResponseException("Cannot create interview. Internal error.");
        }
        return new ResponseEntity<>(interviewDto , HttpStatus.CREATED);
    }
}
