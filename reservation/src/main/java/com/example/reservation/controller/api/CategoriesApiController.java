package com.example.reservation.controller.api;

import com.example.reservation.dto.Categories;
import com.example.reservation.service.CategoriesService;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoriesApiController {
    @Autowired
    CategoriesService categoriesService;

    @GetMapping
    public Map<String, Object> list() {

        List<Categories> list = categoriesService.getCategories();

        int size = list.size();

        Map<String, Object> map = new HashMap<>();
        map.put("size", size);
        map.put("items", list);

        return map;
    }
}
