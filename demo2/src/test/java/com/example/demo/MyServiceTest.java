package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfig2.class})
public class MyServiceTest {
    @Autowired
    MyService myService;

    @Test
    public void execute() throws Exception{
        // given
        int value1 = 5;
        int value2 = 10;

        // when
        int result = myService.execute(value1, value2);

        // then
        Assertions.assertEquals(30, result);
    }
}