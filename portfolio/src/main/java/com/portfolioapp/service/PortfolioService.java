package com.portfolioapp.service;

import com.portfolioapp.entities.Portfolio;
import com.portfolioapp.entities.PortfolioResponse;
import com.portfolioapp.entities.Profile;
import com.portfolioapp.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://localhost:8888/SERVICE-PROFILE";

    public List<PortfolioResponse> findAll() {
        List<Portfolio> portfolios = portfolioRepository.findAll();
        ResponseEntity<Profile[]> response =
                restTemplate.getForEntity(this.URL + "/api/profiles", Profile[].class);
        Profile[] profiles = response.getBody();
        return portfolios.stream().map((Portfolio portfolio) -> mapToPortfolioResponse(portfolio, profiles)).toList();
    }

    private PortfolioResponse mapToPortfolioResponse(Portfolio portfolio, Profile[] profiles) {
        Profile foundProfile = Arrays.stream(profiles)
                .filter(profile -> profile.getId().equals(portfolio.getIdprofile()))
                .findFirst()
                .orElse(null);

        return PortfolioResponse.builder()
                .id(portfolio.getId())
                .competence(portfolio.getCompetence())
                .Experience(portfolio.getExperience())
                .Project(portfolio.getProject())
                .profile(foundProfile)
                .build();
    }

    public PortfolioResponse findById(int id) throws Exception {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(() ->
                new Exception(("Invalid Portfolio id!")));
        Profile profile = restTemplate.getForObject(this.URL + "/api/profiles/" + portfolio.getIdprofile(), Profile.class);
        return PortfolioResponse.builder()
                .profile(profile)
                .id(portfolio.getId())
                .competence(portfolio.getCompetence())
                .Experience(portfolio.getExperience())
                .Project(portfolio.getProject())
                .build();
    }
    public Portfolio addPortfolio(Portfolio newPortfolio) {
        return  portfolioRepository.save(newPortfolio);
//        Profile profile = restTemplate.getForObject(this.URL + "/api/profiles/" + savedPortfolio.getIdprofile(), Profile.class);
//        return mapToPortfolioResponse(savedPortfolio, profile);
    }

    private PortfolioResponse mapToPortfolioResponse(Portfolio portfolio, Profile profile) {
        return PortfolioResponse.builder()
                .profile(profile)
                .id(portfolio.getId())
                .competence(portfolio.getCompetence())
                .Experience(portfolio.getExperience())
                .Project(portfolio.getProject())
                .build();
    }



}
