package com.example.PrincessCyphers.CoronavirusTracker.controllers;

import com.example.PrincessCyphers.CoronavirusTracker.models.AreaStats;
import com.example.PrincessCyphers.CoronavirusTracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller             // Controller type selected allows rendering of HTML ui
public class HomeController {

    @Autowired
    CoronavirusDataService coronavirusDataService;

    @GetMapping ("/")               //when there's mapping to root directory, then call home.
    // modelling allows data to be set in relevant context of what's rendering the page. for here, it's HTML
    public String home(Model model) {
        List<AreaStats> allStats = coronavirusDataService.getAllStats();
        int totalOfCases = allStats.stream().mapToInt(stat -> stat.getLatestNumberOfCases()).sum();
        model.addAttribute("areaStats", allStats);
        model.addAttribute("totalOfCases", totalOfCases);
        return "home";
    }
}
