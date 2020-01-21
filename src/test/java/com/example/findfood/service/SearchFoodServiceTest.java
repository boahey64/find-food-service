package com.example.findfood.service;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.services.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class SearchFoodServiceTest {
    private SearchFoodService serviceUnderTest = new SearchFoodService();

    @Test
    public void name() {
        Response<CompactFood> actual = serviceUnderTest.searchFoodItems();

        assertNotNull(actual);
    }
}