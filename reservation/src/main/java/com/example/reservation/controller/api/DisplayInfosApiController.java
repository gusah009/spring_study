package com.example.reservation.controller.api;

import com.example.reservation.dto.DisplayInfos;
import com.example.reservation.service.DisplayInfosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/displayinfos")
public class DisplayInfosApiController {
    @Autowired
    DisplayInfosService displayinfosService;

    @GetMapping
    public Map<String, Object> list(@RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
                                    @RequestParam(name="start", required=false, defaultValue="0") int start) {
        List<DisplayInfos> list;
        if (categoryId == 0) {
            list = displayinfosService.getDisplayInfos(start);
        } else {
            list = displayinfosService.getDisplayInfoByCategoryId(start, categoryId);
        }

        int size = list.size();

        Map<String, Object> map = new HashMap<>();
        map.put("productCount", size);
        map.put("products", list);

        return map;
    }
}
