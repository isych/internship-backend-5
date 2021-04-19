package com.exadel.backendservice.controllers;

import com.exadel.backendservice.config.AbstractTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
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
                .andExpect(content().string(containsString("[\"ADMIN\",\"TECH\",\"SUPERADMIN\"]")));
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

        this.mockMvc.perform(post("/api/users/register")
                .contentType("application/json")
                .content( "{ \"email\": \"afasfadfad@asfasdasdf.ty\", \"fio\": \"adsfasdfadf\", \"login\": \"adfadfadfafadfad\", \"password\": \"adfafafafasd\", \"role\": \"sdfadsfadsf\"}")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("User not created")));
    }
}