package com.example.spring_mvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.spring_mvc.dto.User;

@Controller
public class UserController {
    @RequestMapping(path="/userform", method=RequestMethod.GET)
    public String userform() {
        return "userForm";
    }

    @RequestMapping(path="/regist", method=RequestMethod.POST)
    public String regist(@ModelAttribute User user) {

        System.out.println("사용자가 입력한 user 정보입니다. 해당 정보를 이용하는 코드가 와야합니다.");
        System.out.println(user);
        return "regist";
    }
}