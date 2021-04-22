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
class CandidateControllerTest extends AbstractTestConfig {

    @Autowired
    MockMvc mockMvc;

    @Test
    void registerCandidate() throws Exception {
        this.mockMvc.perform(post("/api/candidates")
                .contentType("application/json")
                .content("{\n" +
                        "  \"city\": \"Minsk\",\n" +
                        "  \"email\": \"test@fortest.com\",\n" +
                        "  \"event\": \"Java & Js 2021\",\n" +
                        "  \"fullName\": \"Test User\",\n" +
                        "  \"phone\": \"+123456789\",\n" +
                        "  \"preferredTime\": \"FROM_FOUR_TO_SIX\",\n" +
                        "  \"primaryTech\": \"Java\",\n" +
                        "  \"skype\": \"live:.cid.60e00e96f4\",\n" +
                        "  \"summary\": \"\"\n" +
                        "}")
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"id\":\"1\",\"fullName\":\"Test User\",\"email\":\"test@fortest.com\",\"cv\":null,\"cvPath\":null}"));
    }

    @Test
    void getDetailedCandidateDtoById() throws Exception {
        this.mockMvc.perform(get("/api/candidates/?id=1001"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":\"1001\",\"fullName\":\"Nick Nikolaev\",\"summary\":\"\",\"status\":\"RED\",\"phone\":\"+964493431\",\"email\":\"test@testtest.com\",\"skype\":\"live:.cid.60e0d1dadfe72c0e96f4\",\"city\":\"Minsk\",\"country\":\"Belarus\",\"preferredTime\":\"FROM_FOUR_TO_SIX\",\"eventName\":\"Java & Js 2021\",\"interviewProcess\":\"WAITING\",\"interviews\":null}"));
    }


    @Test
    void getStatuses() throws Exception {
        this.mockMvc.perform(get("/api/candidates/statuses"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[\"GREEN\",\"RED\",\"YELLOW\"]"));
    }

    @Test
    void getInterviewStatuses() throws Exception {
        this.mockMvc.perform(get("/api/candidates/interview-statuses"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[\"WAITING\",\"FIRST_INTERVIEW_PASSED\",\"SECOND_INTERVIEW_PASSED\"]"));
    }

    @Test
    void getPreferredTime() throws Exception {
        this.mockMvc.perform(get("/api/candidates/preferred-times"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[\"NONE\",\"FROM_TEN_TO_TWELVE\",\"FROM_TWELVE_TO_TWO\",\"FROM_TWO_TO_FOUR\",\"FROM_FOUR_TO_SIX\"]"));
    }
}