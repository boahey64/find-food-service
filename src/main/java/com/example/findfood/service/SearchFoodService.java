package com.example.findfood.service;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.services.FatsecretService;
import com.fatsecret.platform.services.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchFoodService {
    private static final Logger log = LoggerFactory.getLogger(SearchFoodService.class);

    public Response<CompactFood> searchFoodItems() {
        String key = "ca406771bb6b41bf89f1d9039a0c3496";
        String secret = "1c1d7d806b32493b8963d993b96f63b9";
        FatsecretService service = new FatsecretService(key, secret);

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
