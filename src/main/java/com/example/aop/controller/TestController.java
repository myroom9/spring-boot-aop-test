package com.example.aop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/")
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        log.info("controller");
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
