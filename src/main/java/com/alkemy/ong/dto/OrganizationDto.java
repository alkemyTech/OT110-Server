package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrganizationDto {

    private Long id;
    private String name;
    private String image;
    private String address;
    private Long phone;
    private String email;
    private String welcomeText;
    private String aboutUsText;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
}
