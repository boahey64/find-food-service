package com.example.findfood.controller;

import com.example.findfood.service.FoodService;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.services.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food")
public class SearchFoodController {
    private static final Logger log = LoggerFactory.getLogger(SearchFoodController.class);
    private FoodService foodService;

    @Autowired
    public SearchFoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/search")
    public Response<CompactFood> search(
            @RequestParam String query
    ) {
        log.info("query: {}", query);
        return foodService.searchFoodItems(query);
    }

}
