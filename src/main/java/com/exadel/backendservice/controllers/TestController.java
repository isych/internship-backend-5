package com.exadel.backendservice.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * Пример rest-контроллера с подключенным swagger
 */

@Hidden
@RestController
@Tag(name="Тестовый контроллер", description="Проверка работоспособности swagger")
@RequestMapping("/api/")
public class TestController {

    @GetMapping("gethello")
    @Operation(
            summary = "Отображение приветсвия на странице",
            description = "Отображает Hello, World!!! на странице"
    )
    public String getHello(){
        return "Hello, World!!!";
    }

    @PostMapping("/hello/{username}")
    @Operation(
            summary = "Отображает приветсвие для пользователя с именем username на страенице",
            description = "Отображает приветсвие для пользователя с именем username на страенице"
    )
    public String helloUser(@PathVariable @Parameter(description = "Задает имя пользователя") String username){
        return "Hello, " + username;
    }

}
