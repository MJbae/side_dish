package com.mj_eno.sidedish.web;

import com.mj_eno.sidedish.service.DishService;
import com.mj_eno.sidedish.web.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DishController {

    public final DishService dishService;
    private final Logger logger = LoggerFactory.getLogger(DishController.class);

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/best")
    public List<BestDishResponseDTO> viewAllBestDishes() {
        logger.info("모든 best 메뉴 요청");
        return dishService.findAllBestDish();
    }

    @GetMapping("/best/{categoryId}")
    public BestDishResponseDTO viewBestDishesByCategory(@PathVariable Long categoryId) {
        logger.info(categoryId + "번 카테고리의 best 메뉴 요청");
        return dishService.findAllBestDishByCategoryIdAndRandom(categoryId);
    }

    @GetMapping("/{category}")
    public List<DishResponseDTO> viewDishes(@PathVariable String category) {
        logger.info("모든 " + category + " 메뉴 요청");
        return dishService.findAllDishByCategory(category);
    }

    @GetMapping("/detail/{hash}")
    public DetailDishResponseDTO viewDetailDish(@PathVariable String hash) {
        logger.info(hash + " 값에 해당하는 메뉴의 상세 정보 요청");
        return dishService.getDetailMenu(hash);
    }

    @GetMapping("/recommend/{limit}")
    public List<DishResponseDTO> viewRecommendDish(@PathVariable int limit) {
        logger.info(limit + "개의 랜덤 메뉴 요청");
        return dishService.findDishByRandomLimit(limit);
    }

    @PatchMapping("/order")
    public OrderDishResponseDTO orderDish(@RequestBody OrderDishRequestDTO requestDTO) {
        logger.info("주문 요청");
        return dishService.orderDish(requestDTO);
    }
}
