package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {ApplicationConfig2.class})
public class MyServiceTestMockito {
    @InjectMocks
    MyService myService;

    @Mock
    CalculatorService calculatorService;

    @Test
    public void execute() throws Exception{
        // given
        int value1 = 5;
        int value2 = 10;
        given(calculatorService.plus(5, 10)).willReturn(15);

        // when
        int result = myService.execute(value1, value2);

        // then
        verify(calculatorService).plus(anyInt(), anyInt());
        Assertions.assertEquals(30, result);
    }
}