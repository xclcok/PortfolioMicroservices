package com.portfolioapp.entities;


import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    private Long id;
    private String name;
    private String email;
    private String title;
}
