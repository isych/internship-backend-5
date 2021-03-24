package com.exadel.backendservice.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * Пример rest-контроллера с подключенным swagger
 */

//TODO Delete in the future

@Hidden
@RestController
@Tag(name="Тестовый контроллер", description="Проверка работоспособности swagger")
@RequestMapping("/api/")
public class TestController {

    @GetMapping("gethello")
    @Operation(
            summary = "Отображение приветствия на странице",
            description = "Отображает Hello, World!!! на странице"
    )
    public String getHello(){
        return "Hello, World!!!";
    }

    @PostMapping("/hello/{username}")
    @Operation(
            summary = "Отображает приветствие для пользователя с именем username на странице",
            description = "Отображает приветствие для пользователя с именем username на странице"
    )
    public String helloUser(@PathVariable @Parameter(description = "Задает имя пользователя") String username){
        return "Hello, " + username;
    }

}
