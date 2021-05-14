package com.subadvisor.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public @ResponseBody
    String greeting() {
        return "Hey there, this is the backend-service for sub-advisor!";
    }
}
