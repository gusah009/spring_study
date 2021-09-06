package com.example.layerd_architecture.dto;


public class CalculatorResult {
    // 아래의 상수값은 enum으로 변경 가능하다.
    public static final String PLUS_OPERATION = "+";
    public static final String MINUS_OPERATION = "-";

    private int value1;
    private int value2;
    private String operation;
    private int result;

    public int getValue1() {
        return value1;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}