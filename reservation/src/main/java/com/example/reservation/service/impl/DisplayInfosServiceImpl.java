package com.example.reservation.service.impl;

import com.example.reservation.dao.DisplayInfosDao;
import com.example.reservation.dto.DisplayInfos;
import com.example.reservation.service.DisplayInfosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DisplayInfosServiceImpl implements DisplayInfosService {
    @Autowired
    DisplayInfosDao DisplayInfos;

    @Override
    @Transactional
    public List<DisplayInfos> getDisplayInfos(Integer start) {
        List<DisplayInfos> list = DisplayInfos.selectAll(start);
        return list;
    }

    @Override
    @Transactional
    public List<DisplayInfos> getDisplayInfoByCategoryId(Integer start, Integer categoryId) {
        List<DisplayInfos> list = DisplayInfos.selectCategory(start, categoryId);
        return list;
    }
}
