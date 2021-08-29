package com.example.spring.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.spring.config.ApplicationConfig;
import com.example.spring.dao.RoleDao;
import com.example.spring.dto.Role;

public class SelectAllTest {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        RoleDao roleDao =ac.getBean(RoleDao.class);

        List<Role> list = roleDao.selectAll();

        for(Role role: list) {
            System.out.println(role);
        }

    }

}