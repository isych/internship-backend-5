package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.req.CreateInterviewDto;
import com.exadel.backendservice.dto.resp.InterviewRespDto;
import com.exadel.backendservice.exception.ApiRequestException;
import com.exadel.backendservice.exception.ApiResponseException;
import com.exadel.backendservice.model.ObjectForFeedbackPage;
import com.exadel.backendservice.service.InterviewService;
import com.exadel.backendservice.service.utils.RestAnswer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interviews/")
@Api(tags = "Контроллер для работы с интервью")
public class InterviewController {

    private final InterviewService interviewService;
    private final RestAnswer restAnswer;


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
    public ResponseEntity<?> changeInterviewer(@PathVariable("interviewId") UUID interviewId,
                                               @PathVariable("employeeId") UUID employeeId) {
        return new ResponseEntity<>(interviewService.updateInterviewer(interviewId, employeeId), HttpStatus.OK);
    }

    /**
     * Метод сохранения нового фидбэка
     *
     * @param id - идентификационный номер интервью, для которого необходимо сохранить новый фидбэк по резульиатам этого интервью
     * @param feedback - строка для хранения фидбэка
     */
//    @ApiOperation(value = "Метод сохранения нового фидбэка")
//    @PutMapping(value = "/{id}/feedbacks/save")
//    public ResponseEntity<?> setFeedback(@PathVariable("id") UUID id, String feedback) {
//        return new ResponseEntity<>(interviewService.saveFeedback(id, feedback) , HttpStatus.OK);
//    }

    /**
     * Метод сохранения нового фидбэка
     *
     * @param hash - hash для идентификации интервью
     * @param feedback - строка для хранения фидбэка
     */
    @ApiOperation(value = "Метод сохранения фидбэка для интервью")
    @PutMapping(value = "/feedback/{hash}")
    public ResponseEntity<?> setFeedback(@PathVariable String hash, @RequestBody String feedback) {
        return restAnswer.doResultAjax(interviewService.saveFeedback(hash, feedback));
    }

    /**
     * Метод получения информации для страницы фидбэка
     *
     * @param hash - hash для идентификации интервью
     */
    @ApiOperation(value = "Метод получения информации для страницы фидбэка")
    @GetMapping("/feedback/{hash}")
    public ResponseEntity<?> getPageForFeedback(@PathVariable String hash){
        ObjectForFeedbackPage objectForFeedbackPage = interviewService.getObjectForFeedbackPage(hash);
        return restAnswer.doResultAjax(objectForFeedbackPage);
    }



    /**
     * Метод для удаления интервью
     *
     * @param id - идентификационный номер интервью, которое подлежит удалению
     */
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Integer> deleteById(@PathVariable (value="id") UUID id){
        if(!interviewService.deleteById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
