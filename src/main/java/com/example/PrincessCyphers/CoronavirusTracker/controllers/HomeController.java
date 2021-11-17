package com.example.PrincessCyphers.CoronavirusTracker.controllers;

import com.example.PrincessCyphers.CoronavirusTracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller             // Controller type selected allows rendering of HTML ui
public class HomeController {

    @Autowired
    CoronavirusDataService coronavirusDataService;

    @GetMapping ("/")               //when there's mapping to root directory, then call home.
    // modelling allows data to be set in relevant context of what's rendering the page. for here, it's HTML
    public String home(Model model) {
        model.addAttribute("areaStats", coronavirusDataService.getAllStats());
        return "home";
    }
}
