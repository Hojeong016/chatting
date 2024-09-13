package com.example.chatting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    public @ResponseBody String index() {
        return "Hello World";
    }

    @GetMapping("/send")
    public String put() {
        return "/chat";
    }
}
