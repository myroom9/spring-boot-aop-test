package com.example.aop.service;

import com.example.aop.dto.TestDto;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public String test(String test) {
        return "11111";
    }

    public TestDto test(String test, long test1, TestDto t) {
        return TestDto.builder().test("11").test2("33").build();
    }

    public TestDto test() {
        return TestDto.builder().test("11").test2("33").build();
    }
}
