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
class UserControllerTest extends AbstractTestConfig {

    @Autowired
    UserController userController;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllRoles() throws Exception {
        this.mockMvc.perform(get("/api/users/getAllRoles"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[\"ADMIN\",\"TECH\",\"SUPERADMIN\"]")));
    }

    @Test
    void registerUser() throws Exception {
        this.mockMvc.perform(post("/api/users/register")
                    .contentType("application/json")
                    .content( "{ \"email\": \"afasfadfad@asfasdasdf.ty\", \"fio\": \"adsfasdfadf\", \"login\": \"adfadfadfafadfad\", \"password\": \"adfafafafasd\", \"role\": \"admin\"}")
                 )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("User created")));

        // проверка для не существующей роли
        this.mockMvc.perform(post("/api/users/register")
                    .contentType("application/json")
                    .content( "{ \"email\": \"fasfaadfdfad@asfasdasdf.ty\", \"fio\": \"dsfddffasdfadf\", \"login\": \"dfadfadfadfdfdffadfad\", \"password\": \"dfafdfdfdfafafasd\", \"role\": \"sdfadsfadsf\"}")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("User not created")));
    }

    @Test
    void auth() throws Exception {
        this.mockMvc.perform(post("/api/users/auth")
                    .contentType("application/json")
                    .content( "{ \"login\": \"test-admin\", \"password\": \"1\"}")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(matchesRegex("\\{\"token\":\"[\\w_\\-]{20}\\.[\\w_\\-]{72}\\.[\\w_\\-]{43}\"}")));

        // проверка при введении не правильных данных для аутентификации
        this.mockMvc.perform(post("/api/users/auth")
                    .contentType("application/json")
                    .content( "{ \"login\": \"test\", \"password\": \"test123\"}")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"token\":\"null\"}")));
    }
}