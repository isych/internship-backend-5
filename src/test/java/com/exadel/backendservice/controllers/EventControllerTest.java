package com.exadel.backendservice.controllers;

import com.exadel.backendservice.config.AbstractTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class EventControllerTest extends AbstractTestConfig {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getEventTypes() throws Exception {
        this.mockMvc.perform(get("/api/events/types"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[\"MEETUP\",\"INTERNSHIP\",\"TRAINING\"]")));
    }

    @Test
    void getEvent() throws Exception {
        this.mockMvc.perform(get("/api/events/201398a0-a79f-11eb-bcbc-0242ac130001"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":\"201398a0-a79f-11eb-bcbc-0242ac130001\",\"name\":\"Java & Js 2019\",\"startDate\":\"2003-01-01T00:00:00\",\"description\":\"Java is a high-level programming language developed by Sun Microsystems. The Java syntax is similar to C++, but is strictly an object-oriented programming language. ... For example, most Java programs contain classes, which are used to define objects, and methods, which are assigned to individual classes.\",\"type\":\"MEETUP\",\"locations\":[],\"techs\":[{\"id\":\"f8fcba8e-a79f-11eb-bcbc-0242ac130001\",\"name\":\"Java\"},{\"id\":\"f8fcba8e-a79f-11eb-bcbc-0242ac130003\",\"name\":\"JavaScript\"}],\"eventStatus\":\"PUBLISHED\"}")));

    }

    @Test
    void checkUniqueness() throws Exception {
        this.mockMvc.perform(get("/api/events/uniqueness/JavaScript"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("true")));
    }

    @Test
    void createEvent() throws Exception {
        this.mockMvc.perform(post("/api/events/create")
                .contentType("application/json")
                .content("{\n" +
                        "  \"cities\": [\n" +
                        "    \"Minsk\"\n" +
                        "  ],\n" +
                        "  \"description\": \"string\",\n" +
                        "  \"endDate\": \"2021-04-30T06:41:08.637Z\",\n" +
                        "  \"name\": \"Java_2021\",\n" +
                        "  \"startDate\": \"2021-04-30T06:41:08.637Z\",\n" +
                        "  \"techs\": [\n" +
                        "    \"Java\"\n" +
                        "  ],\n" +
                        "  \"type\": \"INTERNSHIP\"\n" +
                        "}")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Java_2021\",\"pictureName\":null,\"picturePath\":null")));

    }

    @Test
    void checkImage() throws Exception {
        this.mockMvc.perform(get("/api/events/201398a0-a79f-11eb-bcbc-0242ac130003/image/exists"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("false")));

    }

    @Test
    void publishEvent() throws Exception {
        this.mockMvc.perform(get("/api/events/201398a0-a79f-11eb-bcbc-0242ac130003/publish"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":\"201398a0-a79f-11eb-bcbc-0242ac130003\",\"name\":\"Spring 2019\",\"startDate\":\"2003-01-01T00:00:00\",\"description\":\"Being Spring developers, we naturally choose components from the Spring stack to do all the heavy lifting. After all, we have the concept etched out, so we're already halfway there.\",\"type\":\"TRAINING\",\"locations\":[],\"techs\":[{\"id\":\"f8fcba8e-a79f-11eb-bcbc-0242ac130002\",\"name\":\"Spring Stack\"}],\"eventStatus\":\"PUBLISHED\"}")));

    }

    @Test
    void eventToArchive() throws Exception {
        this.mockMvc.perform(get("/api/events/201398a0-a79f-11eb-bcbc-0242ac130007/toarchive"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":\"201398a0-a79f-11eb-bcbc-0242ac130007\",\"name\":\"Java 2020\",\"startDate\":\"2003-01-01T00:00:00\",\"description\":\"Java is a high-level programming language developed by Sun Microsystems. The Java syntax is similar to C++, but is strictly an object-oriented programming language. ... For example, most Java programs contain classes, which are used to define objects, and methods, which are assigned to individual classes.\",\"type\":\"INTERNSHIP\",\"locations\":[],\"techs\":[{\"id\":\"f8fcba8e-a79f-11eb-bcbc-0242ac130001\",\"name\":\"Java\"},{\"id\":\"f8fcba8e-a79f-11eb-bcbc-0242ac130002\",\"name\":\"Spring Stack\"}],\"eventStatus\":\"ARCHIVED\"}")));

    }
}