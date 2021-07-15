package com.example.hp_myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {

    @GetMapping("/privacy")
    public String privacy(){
        return "privacy";
    }

    @GetMapping("/terms")
    public String terms(){
        return "terms";
    }
}
