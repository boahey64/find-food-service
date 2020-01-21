package com.example.findfood.controller;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(JUnitPlatform.class)
@SpringBootTest
@AutoConfigureMockMvc
class SearchFoodControllerIT {

    @Autowired
    private MockMvc mvc;

    @Test
    public void call_with_param_query() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/foods/search")
                .param("query", "asc"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void call_without_param_query() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/foods/search"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}