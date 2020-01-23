package com.example.findfood.service;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.FatsecretService;
import com.fatsecret.platform.services.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FatSecretAdapter {

    private String oauthClientId;
    private String oauthClientSecret;

    private FatsecretService fatsecretService;

    public FatSecretAdapter(
            @Value("${oauth.client.id}") String oauthClientId,
            @Value("${oauth.client.secret}") String oauthClientSecret
    ) {
        this.oauthClientId = oauthClientId;
        this.oauthClientSecret = oauthClientSecret;

        fatsecretService = new FatsecretService(oauthClientId, oauthClientSecret);
    }

    public Response<CompactFood> searchFoodItems(String query, Integer page) {
        return fatsecretService.searchFoods(query, page);
    }

    public Food getFoodItem(Long id) {
        return fatsecretService.getFood(id);
    }

}
