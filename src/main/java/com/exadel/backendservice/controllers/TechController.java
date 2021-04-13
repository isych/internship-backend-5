package com.exadel.backendservice.controllers;

import com.exadel.backendservice.service.TechService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tech")
@RequiredArgsConstructor
@Api(tags = "Контроллер для работы с технологиями/языками программирования")
public class TechController {

    private final TechService techService;

    @ApiOperation("Метод для получения списка всех технологий")
    @GetMapping
    public ResponseEntity<List<String>> getAllTech() {
        return new ResponseEntity<>(techService.getTechList(), HttpStatus.OK);
    }


}
