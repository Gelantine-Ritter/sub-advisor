package com.subadvisor.backend.Home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestController {

    TestController(){}

    @RequestMapping("/")
    public @ResponseBody
    String greeting() {
        return "Hey there, this is the backend-service for sub-advisor!";
    }
}
