package com.portfolioapp.controller;

import com.portfolioapp.entities.Portfolio;
import com.portfolioapp.entities.PortfolioResponse;
import com.portfolioapp.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
@CrossOrigin(origins = "http://localhost:4200/")// Change the base path to include "/portfolios"
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping
    public List<PortfolioResponse> findAll() {
        return portfolioService.findAll();
    }

    @GetMapping("/{id}")
    public PortfolioResponse findById(@PathVariable("id") int id) throws Exception {
        return portfolioService.findById(id);
    }

    @PostMapping("/add")
    public Portfolio addPortfolio(@RequestBody Portfolio newPortfolio) {
        Portfolio addedPortfolio = portfolioService.addPortfolio(newPortfolio);
        return addedPortfolio;
    }
}
