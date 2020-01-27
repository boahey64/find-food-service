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

    private FatSecretFacade fatSecretFacade;

    @Autowired
    public FoodService(FatSecretFacade fatSecretFacade) {
        this.fatSecretFacade = fatSecretFacade;
    }

    public Response<CompactFood> searchFoodItems(String query, Integer page) {
        if(query.isEmpty()) {
            return null;
        }

        Response<CompactFood> response = fatSecretFacade.searchFoodItems(query, page);
        log.info("response: {}", response);
        return response;
    }

    public Food getFoodItem(Long id) {
        String key = "Replace this by your Application Consumer Key";
        String secret = "Replace this by your Consumer Secret";

        return fatSecretFacade.getFoodItem(id);
    }

}
