package com.exadel.backendservice.controllers;

import com.exadel.backendservice.config.AbstractTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class LocationControllerTest extends AbstractTestConfig {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getCountriesWithCitiesDto() throws Exception {
        this.mockMvc.perform(get("/api/location/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"name\":\"Ukraine\",\"cities\":[\"Kyiv\",\"Lviv\",\"Odesa\",\"Kharkov\"]},{\"name\":\"Belarus\",\"cities\":[\"Brest\",\"Dzyarzhynsk\",\"Slutsk\",\"Minsk\"]},{\"name\":\"Uzbekistan\",\"cities\":[\"Tashkent\",\"Samarqand\",\"Namangan\",\"Fergana\"]},{\"name\":\"Poland\",\"cities\":[\"GdaÅ\u0084sk\",\"BiaÅ\u0082ystok\",\"CheÅ\u0082m\",\"Bytom\"]},{\"name\":\"Lithuania\",\"cities\":[\"Alytus\",\"Vilnius\",\"Jonava\",\"Kaunas\"]},{\"name\":\"Germany\",\"cities\":[\"Stuttgart\",\"Berlin\",\"Munich\",\"Hamburg\"]}]")));
    }
}