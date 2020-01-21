package com.example.findfood.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/foods")
public class SearchFoodController {

    @GetMapping("/search")
    public String showSearch() {
        return "Hello Search";
    }

}
