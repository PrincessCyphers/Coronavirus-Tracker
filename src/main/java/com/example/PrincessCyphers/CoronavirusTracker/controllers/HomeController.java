package com.example.PrincessCyphers.CoronavirusTracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller             // Controller type selected allows rendering of HTML ui
public class HomeController {

    @GetMapping ("/")               //when there's mapping to root directory, then call home.
    public String home() {
        return "home";
    }
}
