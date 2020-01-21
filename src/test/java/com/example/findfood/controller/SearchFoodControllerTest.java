package com.example.findfood.controller;

import com.example.findfood.service.FoodService;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.services.Response;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class SearchFoodControllerTest {
    @InjectMocks
    SearchFoodController searchFoodController;

    @Mock
    FoodService foodService;

    private String path = "/api/foods";

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void call_search() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(foodService.searchFoodItems("pasta")).thenReturn(aResponseOfCompactFoodItems());

        Response<CompactFood> actual = searchFoodController.search("pasta");

        assertThat(actual, notNullValue());
        assertEquals(actual.getPageNumber(), 3);
    }


    private Response<CompactFood> aResponseOfCompactFoodItems() {
        Gson g = new Gson();
        String jsonString = "{\"pageNumber\":3,\"maxResults\":50,\"totalResults\":2000,\"results\":[]}";
        return g.fromJson(jsonString, Response.class);
    }
}