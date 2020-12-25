package com.example.aop.controller;

import com.example.aop.annotation.IpLog;
import com.example.aop.dto.TestDto;
import com.example.aop.exception.BaseException;
import com.example.aop.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @IpLog
    @GetMapping("/test")
    public String test() {
        //throw new BaseException("test");

        testService.test("55555");
        log.info("controller");
        testService.test();
        log.info("controller 111");
        testService.test("1111", 11L, TestDto.builder().test("11").build());

        return "test";
    }

    @PostMapping("/test")
    public String test23() {
        log.info("test22");
        return "test";
    }

    @GetMapping("/test/{test}")
    public String test24(@PathVariable("test") String test) {
        log.info("test24");
        return "test";
    }

    @GetMapping("/")
    public String test1() {
        log.info("test");
        return "test";
    }
}
