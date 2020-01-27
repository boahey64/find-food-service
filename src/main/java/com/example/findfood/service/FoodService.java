package com.example.findfood.service;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    private static final Logger log = LoggerFactory.getLogger(FoodService.class);

    private FoodSearchService foodSearchService;

    @Autowired
    public FoodService(FoodSearchService foodSearchService) {
        this.foodSearchService = foodSearchService;
    }

    public Response<CompactFood> searchFoodItems(String query, Integer page) {
        if(query.isEmpty()) {
            return null;
        }

        Response<CompactFood> response = foodSearchService.searchFoodItems(query, page);
        log.info("response: {}", response);
        return response;
    }

    public Food getFoodItem(Long id) {
        return foodSearchService.getFoodItem(id);
    }

}
