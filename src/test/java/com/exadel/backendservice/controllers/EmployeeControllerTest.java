package com.exadel.backendservice.controllers;

import com.exadel.backendservice.config.AbstractTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class EmployeeControllerTest extends AbstractTestConfig {

    @Autowired
    MockMvc mockMvc;

    @Test
    void auth() throws Exception {
        this.mockMvc.perform(post("/api/employee/auth")
                    .contentType("application/json")
                    .content( "{ \"email\": \"tech-2@fortest.ru\", \"password\": \"1\"}")
                 )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(matchesRegex("\\{\"token\":\"[\\w_\\-]{20}\\.[\\w_\\-]{82}\\.[\\w_\\-]{43}\"}")));

        this.mockMvc.perform(post("/api/employee/auth")
                     .contentType("application/json")
                     .content( "{ \"email\": \"admin-1@fortest.ru\", \"password\": \"1\"}")
                 )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(matchesRegex("\\{\"token\":\"[\\w_\\-]{20}\\.[\\w_\\-]{83}\\.[\\w_\\-]{43}\"}")));

        this.mockMvc.perform(post("/api/employee/auth")
                    .contentType("application/json")
                    .content( "{ \"email\": \"superadmin-1@fortest.ru\", \"password\": \"1\"}")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(matchesRegex("\\{\"token\":\"[\\w_\\-]{20}\\.[\\w_\\-]{90}\\.[\\w_\\-]{43}\"}")));

        // проверка при введении не правильных данных для аутентификации
        this.mockMvc.perform(post("/api/employee/auth")
                    .contentType("application/json")
                    .content( "{ \"email\": \"superadmin-1@fortest.ru\", \"password\": \"test123\"}")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"token\":\"null\"}")));
    }

    @Test
    void getAllRoles() throws Exception {
        this.mockMvc.perform(get("/api/employee/getAllRoles"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[\"ADMIN\",\"TECH\",\"SUPERADMIN\"]")));
    }


}