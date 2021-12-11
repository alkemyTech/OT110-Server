package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SlideRequest {

    @NotBlank(message="image cannot be empty")
    private String base64Image;

    @NotBlank(message="text cannot be empty")
    private String text;

    private Integer orderSlide;

}
