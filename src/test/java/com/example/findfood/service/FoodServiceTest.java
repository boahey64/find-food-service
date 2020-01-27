package com.example.findfood.service;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.Response;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {
    @InjectMocks
    FoodService serviceUnderTest;

    @Mock
    FoodSearchService foodSearchService;

    @Test
    public void search_food_items() {
        when(foodSearchService.searchFoodItems("pasta", 0)).thenReturn(aResponseOfCompactFoodItems());

        Response<CompactFood> actual = serviceUnderTest.searchFoodItems("pasta", 0);

        assertNotNull(actual);
    }

    @Test
    public void get_food_item() {
        when(foodSearchService.getFoodItem(77518L)).thenReturn(aFoodItem());

        Food actual = serviceUnderTest.getFoodItem(77518L);

        assertNotNull(actual);
    }

    private Response<CompactFood> aResponseOfCompactFoodItems() {
        Gson g = new Gson();
        String jsonString = "{\"pageNumber\":3,\"maxResults\":50,\"totalResults\":2000,\"results\":[]}";
        return g.fromJson(jsonString, Response.class);
    }

    private Food aFoodItem() {
        return new Food();
    }
}