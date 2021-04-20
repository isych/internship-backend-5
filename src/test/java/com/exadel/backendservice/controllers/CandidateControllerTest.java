package com.exadel.backendservice.controllers;

import com.exadel.backendservice.config.AbstractTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CandidateControllerTest extends AbstractTestConfig {
    @Autowired
    MockMvc mockMvc;

    @Test
    void createCandidate() throws Exception {
         this.mockMvc.perform(post("/api/candidates")
                .contentType("application/json")
                .content("{\n" +
                        "  \"city\": \"Minsk\",\n" +
                        "  \"email\": \"anxel1992@gmail.com\",\n" +
                        "  \"event\": \"Java & Js 2021\",\n" +
                        "  \"fullName\": \"Anzhela Khamitskaya\",\n" +
                        "  \"phone\": \"+375293793195\",\n" +
                        "  \"preferredTime\": \"FOUR_SIX\",\n" +
                        "  \"primaryTech\": \"Java\",\n" +
                        "  \"skype\": \"live:.cid.60e0d1e72c0e96f4\",\n" +
                        "  \"summary\": \"\"\n" +
                        "}")
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"id\":\"1\",\"fullName\":\"Anzhela Khamitskaya\",\"status\":\"YELLOW\",\"email\":\"anxel1992@gmail.com\",\"city\":\"Minsk\",\"preferredTime\":\"FOUR_SIX\"}"));
    }

    @Test
    void getDetailedCandidateDtoById() throws Exception {
            this.mockMvc.perform(get("/api/candidates/1001")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":\"1001\",\"fullName\":\"Nick Nikolaev\",\"summary\":\"\",\"status\":\"RED\",\"phone\":\"+964493431\",\"email\":\"test@testtest.com\",\"skype\":\"live:.cid.60e0d1dadfe72c0e96f4\",\"city\":\"Minsk\",\"country\":\"Belarus\",\"preferredTime\":\"FOUR_SIX\",\"eventName\":\"Java & Js 2021\",\"interviewProcess\": \"WAITING\",\"interviews\":\"\"}"));
    }
}
