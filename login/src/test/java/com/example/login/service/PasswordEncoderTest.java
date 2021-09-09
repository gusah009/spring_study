package com.example.login.service;


import com.example.login.config.ApplicationConfig;
import com.example.login.config.SecurityConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class PasswordEncoderTest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void passwordEncode() throws Exception{
        System.out.println(passwordEncoder.encode("1234"));
    }

    @Test
    public void passwordTest() throws Exception{
        String encodePasswd = "$2a$10$USbExG2YOZJqu5rR9eWAqO3NqwjS6c8uI0c695cnURA2gxqRnx41O";
        String password = "1234";
        boolean test = passwordEncoder.matches(password, encodePasswd);
        System.out.println(test);
    }

}