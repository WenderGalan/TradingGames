package com.tradinggames.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Index {

    @GetMapping("")
    public String welcome() {
        return "Welcome to my world !";
    }

    @GetMapping("/")
    public String welcomeAgain() {
        return "This is also my world !";
    }

}
