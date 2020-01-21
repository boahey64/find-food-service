package com.example.findfood.service;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.services.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class FoodServiceTest {
    private FoodService serviceUnderTest = new FoodService(
            "ca406771bb6b41bf89f1d9039a0c3496",
            "1c1d7d806b32493b8963d993b96f63b9"
    );

    @Test
    public void search_food_items() {
        Response<CompactFood> actual = serviceUnderTest.searchFoodItems("pasta");

        assertNotNull(actual);
    }
}