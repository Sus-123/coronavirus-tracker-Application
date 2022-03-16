package com.example.coronavirustracker.controller;

import com.example.coronavirustracker.CoronavirusTrackerApplication;
import com.example.coronavirustracker.model.LocationStats;
import com.example.coronavirustracker.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class MainController {
    @Autowired
    private CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String homeController (Model model) {
        ArrayList<LocationStats> allStates = coronaVirusDataService.getAllStats();
        int totalCases = allStates.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute("locationStats", allStates );
        model.addAttribute("totalCases", totalCases);
        return "home";
    }
}
