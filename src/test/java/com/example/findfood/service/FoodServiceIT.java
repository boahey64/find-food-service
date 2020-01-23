package com.example.findfood.service;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;


public class FoodServiceIT {
    private static final Logger log = LoggerFactory.getLogger(FoodServiceIT.class);

    private int pastaCount = 0;

    FoodService serviceUnderTest;

    FatSecretAdapter fatSecretAdapter;

    @BeforeEach
    void setUp() {
        fatSecretAdapter = new FatSecretAdapter(
                "ca406771bb6b41bf89f1d9039a0c3496",
                "1c1d7d806b32493b8963d993b96f63b9");
        serviceUnderTest = new FoodService(fatSecretAdapter);
    }

    @Test
    public void search_food_items() {
        String searchExpression = "pasta";
        Response<CompactFood> actual = serviceUnderTest.searchFoodItems(searchExpression);

        log.info("actual: {}", actual.getPageNumber());
        log.info("actual: {}", actual.getTotalResults());
        log.info("actual: {}", actual.getMaxResults());

        assertEquals(0, actual.getPageNumber());
        assertEquals(2000, actual.getTotalResults());
        assertEquals(50, actual.getMaxResults());

        assertNotNull(actual.getResults());
        assertNotEquals(true, actual.getResults().isEmpty());

        actual.getResults().forEach(
                item -> {
                    checkAndCountSearchExpression(item, searchExpression);
                }
        );

        assertNotEquals(actual.getMaxResults(), pastaCount);
    }

    @Test
    void get_food_item() {
        Food actual = serviceUnderTest.getFoodItem(77518L);

        assertNotNull(actual);
        assertEquals("Rigatoni Pasta", actual.getName());
    }

    private void checkAndCountSearchExpression(CompactFood compactFood, String searchExpression) {
        if(compactFood.getName().toLowerCase().contains(searchExpression)) {
            pastaCount++;
            log.info("name: {} | {}", pastaCount, compactFood.getName());
        }

    }
}