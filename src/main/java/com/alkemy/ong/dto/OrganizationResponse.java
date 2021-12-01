package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrganizationResponse {
    private String name;
    private String image;
    private String address;
    private Long phone;
    private String email;
    private String welcomeText;
    private String aboutUsText;

}
