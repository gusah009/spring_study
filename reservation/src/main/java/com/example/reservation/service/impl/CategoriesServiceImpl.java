package com.example.reservation.service.impl;

import com.example.reservation.dao.CategoriesDao;
import com.example.reservation.dto.Categories;
import com.example.reservation.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    CategoriesDao categoriesDao;

    @Override
    @Transactional
    public List<Categories> getCategories() {
        List<Categories> list = categoriesDao.selectAll();
        return list;
    }
}
