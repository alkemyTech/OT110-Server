package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter

public class OrganizationPublicDto {

    private String name;
    private String image;
    private String address;
    private Long phone;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;

}
