package com.example.aop.service;

import com.example.aop.dto.TestDto;
import com.example.aop.exception.BaseException;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public String test(String test) {
        throw new BaseException("message");
        // return "11111";
    }

    public TestDto test(String test, long test1, TestDto t) {
        return TestDto.builder().test("11").test2("33").build();
    }

    public TestDto test() {
        return TestDto.builder().test("11").test2("33").build();
    }
}
