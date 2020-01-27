package com.example.findfood.service;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.Response;

public interface FoodSearchService {
    public Response<CompactFood> searchFoodItems(String query, Integer page);
    public Food getFoodItem(Long id);
}

