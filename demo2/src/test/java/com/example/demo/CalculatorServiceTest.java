package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfig2.class})
public class CalculatorServiceTest {
    @Autowired
    CalculatorService calculatorService;

    @BeforeEach
    public void init(){
        this.calculatorService = new CalculatorService();
    }

    @Test
    public void plus() throws Exception{
        // given
        int value1 = 10;
        int value2 = 5;

        // when
        int result = calculatorService.plus(value1, value2);

        // then
        Assertions.assertEquals(15, result); // 결과와 15가 같을 경우에만 성공
    }

    @Test
    public void divide() throws Exception{
        // given
        int value1 = 10;
        int value2 = 5;

        // when
        int result = calculatorService. divide (value1, value2);

        // then
        Assertions.assertEquals(2,result); // 결과와 2가 같을 경우에만 성공
    }

    @Test
    public void divideExceptionTest() throws Exception{
        // given
        int value1 = 10;
        int value2 = 0;

        try {
            calculatorService.divide(value1, value2);
        }catch (ArithmeticException ae){
            Assertions.assertTrue(true); // 이부분이 실행되었다면 성공
            return; // 메소드를 더이상 실행하지 않는다.
        }
        Assertions.fail(); // 이부분이 실행되면 무조건 실패다.

    }
}