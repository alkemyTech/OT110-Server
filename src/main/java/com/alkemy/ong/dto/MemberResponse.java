package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {

    private String name;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    private String image;
    private String description;
    private String dateCreation;
    private String dateUpdate;
}

