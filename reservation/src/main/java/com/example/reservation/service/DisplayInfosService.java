package com.example.reservation.service;

import com.example.reservation.dto.Categories;
import com.example.reservation.dto.DisplayInfos;

import java.util.List;

public interface DisplayInfosService {
    public List<DisplayInfos> getDisplayInfos(Integer start);
    public List<DisplayInfos> getDisplayInfoByCategoryId(Integer start, Integer categoryId);
}