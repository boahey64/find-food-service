package com.example.findfood.controller;

import com.example.findfood.service.FoodService;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    private static final Logger log = LoggerFactory.getLogger(FoodController.class);
    private FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/items")
    public Response<CompactFood> itemsSearch(@RequestParam String query, Optional<Integer> page) {
        log.info("query: {}", query);
        return foodService.searchFoodItems(query, page.orElse(0));
    }

    @GetMapping("/items/{itemId}")
    public Food getFoodItem(@PathVariable Long itemId) {
        log.info("itemId: {}", itemId);
        return foodService.getFoodItem(itemId);
    }

}
