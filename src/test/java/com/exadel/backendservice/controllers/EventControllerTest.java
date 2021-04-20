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
class EventControllerTest extends AbstractTestConfig {

    @Autowired
    EventController eventController;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getEventTypes() throws Exception {
        this.mockMvc.perform(get("/api/events/types"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[\"MEETUP\",\"INTERNSHIP\",\"TRAINING\"]")));

    }

    @Test
    void getEvent() throws Exception {
        this.mockMvc.perform(get("/api/events/JavaScript"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1004,\"name\":\"JavaScript\",\"description\":\"description\",\"startDate\":\"2003-01-01T00:00:00\",\"type\":\"MEETUP\",\"location\":[{\"city\":\"Kharkov\",\"country\":\"Ukraine\"},{\"city\":\"Kyiv\",\"country\":\"Ukraine\"}]}")));

    }

    @Test
    void checkUniqueness() throws Exception {
        this.mockMvc.perform(get("/api/events/uniqueness/JavaScript"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("false")));

        this.mockMvc.perform(get("/api/events/uniqueness/qwe"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("true")));
    }

    @Test
    void createEvent() throws Exception {
        this.mockMvc.perform(post("/api/events/create")
                .contentType("application/json")
                .content("{ \"cities\": [ \"Minsk\" ], \"description\": \"string\", \"endDate\": \"2021-04-19T12:37:13.170Z\", \"name\": \"Java 2021\", \"pictureUrl\": \"string\", \"startDate\": \"2021-06-19T12:37:13.170Z\", \"techs\": [ \"Java\" ], \"type\": \"INTERNSHIP\"}")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"name\":\"Java 2021\",\"description\":\"string\",\"type\":\"INTERNSHIP\"}")));

    }
}