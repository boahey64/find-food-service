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
class FoodControllerIT {

    @Autowired
    private MockMvc mvc;

    @Test
    public void search_with_param_query() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/food/items")
                .param("query", "asc"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void search_without_param_query() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/food/items"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }


    @Test
    public void get_item_with_id() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/food/items/123"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void get_item_without_id() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/food/items"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

}