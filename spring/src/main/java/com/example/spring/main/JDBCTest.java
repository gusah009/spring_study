package com.example.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.spring.config.ApplicationConfig;
import com.example.spring.dao.RoleDao;
import com.example.spring.dto.Role;

public class JDBCTest {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        RoleDao roleDao = ac.getBean(RoleDao.class);

        Role role = new Role();
        role.setRoleId(202);
        role.setDescription("FIREFIGHTER");

        int insertCount = roleDao.insert(role);
        System.out.println(insertCount + " 건 입력하였습니다.");

        int updateCount = roleDao.update(role);
        System.out.println(updateCount +  " 건 수정하였습니다.");

        Role resultRole = roleDao.selectById(201);
        System.out.println(resultRole);

        int deleteCount = roleDao.deleteById(501);
        System.out.println(deleteCount + "건 삭제하였습니다.");

        Role resultRole2 = roleDao.selectById(501);
        System.out.println(resultRole2);

    }

}