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

    private int page0SearchItemCount = 0;
    private int page1SearchItemCount = 0;

    FoodService serviceUnderTest;

    FatSecretFacade fatSecretFacade;

    @BeforeEach
    void setUp() {
        fatSecretFacade = new FatSecretFacade(
                "ca406771bb6b41bf89f1d9039a0c3496",
                "1c1d7d806b32493b8963d993b96f63b9");
        serviceUnderTest = new FoodService(fatSecretFacade);

        page0SearchItemCount = 0;
        page1SearchItemCount = 0;
    }

    @Test
    public void search_food_items_0() {
        String searchExpression = "pasta";
        Response<CompactFood> actual = serviceUnderTest.searchFoodItems(searchExpression, 0);

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
                    if(checkAndCountSearchExpression(item, searchExpression))
                        page0SearchItemCount++;
                }
        );

        assertNotEquals(actual.getMaxResults(), page0SearchItemCount);
    }


    @Test
    public void search_food_items_page_1() {
        String searchExpression = "pasta";
        Response<CompactFood> actual = serviceUnderTest.searchFoodItems(searchExpression, 1);

        log.info("actual: {}", actual.getPageNumber());
        log.info("actual: {}", actual.getTotalResults());
        log.info("actual: {}", actual.getMaxResults());

        assertEquals(1, actual.getPageNumber());
        assertEquals(2000, actual.getTotalResults());
        assertEquals(50, actual.getMaxResults());

        assertNotNull(actual.getResults());
        assertNotEquals(true, actual.getResults().isEmpty());

        actual.getResults().forEach(
                item -> {
                    if(checkAndCountSearchExpression(item, searchExpression))
                        page1SearchItemCount++;
                }
        );

        assertNotEquals(actual.getMaxResults(), page0SearchItemCount);
    }

    @Test
    public void compare_food_items_page_0_and_1() {
        String searchExpression = "pasta";
        Response<CompactFood> page0 = serviceUnderTest.searchFoodItems(searchExpression, 0);

        page0.getResults().forEach(
                item -> {
                    if(checkAndCountSearchExpression(item, searchExpression))
                        page0SearchItemCount++;
                }
        );
        assertEquals(0, page0.getPageNumber());

        Response<CompactFood> page1 = serviceUnderTest.searchFoodItems(searchExpression, 1);

        page1.getResults().forEach(
                item -> {
                    if(checkAndCountSearchExpression(item, searchExpression))
                        page1SearchItemCount++;
                }
        );

        assertEquals(1, page1.getPageNumber());

        assertNotEquals(page1SearchItemCount, page0SearchItemCount);
    }


    @Test
    void get_food_item() {
        Food actual = serviceUnderTest.getFoodItem(77518L);

        assertNotNull(actual);
        assertEquals("Rigatoni Pasta", actual.getName());
    }

    private boolean checkAndCountSearchExpression(CompactFood compactFood, String searchExpression) {
        if(compactFood.getName().toLowerCase().contains(searchExpression)) {
            log.info("name: {} | {}", compactFood.getName());
            return true;
        }
        return false;
    }
}