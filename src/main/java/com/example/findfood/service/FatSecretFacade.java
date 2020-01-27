package com.example.findfood.service;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.FatsecretService;
import com.fatsecret.platform.services.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FatSecretFacade implements FoodSearchService {

    private String oauthClientId;
    private String oauthClientSecret;

    private FatsecretService fatsecretService;

    public FatSecretFacade(
            @Value("${oauth.client.id}") String oauthClientId,
            @Value("${oauth.client.secret}") String oauthClientSecret
    ) {
        this.oauthClientId = oauthClientId;
        this.oauthClientSecret = oauthClientSecret;

        fatsecretService = new FatsecretService(oauthClientId, oauthClientSecret);
    }

    @Override
    public Response<CompactFood> searchFoodItems(String query, Integer page) {
        return fatsecretService.searchFoods(query, page);
    }

    @Override
    public Food getFoodItem(Long id) {
        return fatsecretService.getFood(id);
    }
}
