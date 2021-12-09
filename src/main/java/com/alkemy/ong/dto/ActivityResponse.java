package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponse {

    private String name;
    private String content;
    private String image;
    private String dateCreation;
    private String dateUpdate;

}
