package com.example.findfood.service;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.FatsecretService;
import com.fatsecret.platform.services.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    private static final Logger log = LoggerFactory.getLogger(FoodService.class);

    private String oauthClientId;
    private String oauthClientSecret;

    private FatsecretService fatsecretService;

    public FoodService(
            @Value("${oauth.client.id}") String oauthClientId,
            @Value("${oauth.client.secret}") String oauthClientSecret
    ) {
        this.oauthClientId = oauthClientId;
        this.oauthClientSecret = oauthClientSecret;

        fatsecretService = new FatsecretService(oauthClientId, oauthClientSecret);
    }

    public Response<CompactFood> searchFoodItems(String query) {
        if(query.isEmpty()) {
            return null;
        }

        Response<CompactFood> response = fatsecretService.searchFoods(query);
        //This response contains the list of food items at zeroth page for your query

        List<CompactFood> results = response.getResults();
        //This list contains summary information about the food items

        Response<CompactFood> responseAtPage3 = fatsecretService.searchFoods(query, 3);
        //This response contains the list of food items at page number 3 for your query
        //If total results are less, then this response will have empty list of the food items
        log.info("responseAtPage3: {}", responseAtPage3);
        return responseAtPage3;
    }

    public Food getFoodItem(Long id) {
        String key = "Replace this by your Application Consumer Key";
        String secret = "Replace this by your Consumer Secret";

        return fatsecretService.getFood(id);
    }

}
