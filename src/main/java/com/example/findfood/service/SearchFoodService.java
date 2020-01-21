package com.example.findfood.service;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.services.FatsecretService;
import com.fatsecret.platform.services.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchFoodService {
    private static final Logger log = LoggerFactory.getLogger(SearchFoodService.class);

    private String oauthClientId;
    private String oauthClientSecret;

    public SearchFoodService(
            @Value("${oauth.client.id}") String oauthClientId,
            @Value("${oauth.client.secret}") String oauthClientSecret
    ) {
        this.oauthClientId = oauthClientId;
        this.oauthClientSecret = oauthClientSecret;
    }

    public Response<CompactFood> searchFoodItems() {
        FatsecretService service = new FatsecretService(oauthClientId, oauthClientSecret);

        String query = "pasta"; //Your query string
        Response<CompactFood> response = service.searchFoods(query);
        //This response contains the list of food items at zeroth page for your query

        List<CompactFood> results = response.getResults();
        //This list contains summary information about the food items

        Response<CompactFood> responseAtPage3 = service.searchFoods(query, 3);
        //This response contains the list of food items at page number 3 for your query
        //If total results are less, then this response will have empty list of the food items
        log.info("responseAtPage3: {}", responseAtPage3);
        return responseAtPage3;
    }

}
