package com.portfolioapp.entities;


import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioResponse {
    private Long id;
    private String competence;
    private String Experience;
    private String Project;
    private Profile profile;
}
