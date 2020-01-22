package com.example.findfood.service;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.FatsecretService;
import com.fatsecret.platform.services.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    private static final Logger log = LoggerFactory.getLogger(FoodService.class);

    private FatSecretAdapter fatSecretAdapter;

    @Autowired
    public FoodService(FatSecretAdapter fatSecretAdapter) {
        this.fatSecretAdapter = fatSecretAdapter;
    }

    public Response<CompactFood> searchFoodItems(String query) {
        if(query.isEmpty()) {
            return null;
        }

        Response<CompactFood> response = fatSecretAdapter.searchFoodItems(query);
        log.info("response: {}", response);
        return response;
    }

    public Food getFoodItem(Long id) {
        String key = "Replace this by your Application Consumer Key";
        String secret = "Replace this by your Consumer Secret";

        return fatSecretAdapter.getFoodItem(id);
    }

}
