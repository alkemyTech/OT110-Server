package com.alkemy.ong.dto;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {

    private String name;
    private String content;
    private String image;
    private String dateCreation;
    private String dateUpdate;


}
