package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SlideRequest {

    @NotBlank(message="image cannot be empity")
    private String base64Image;

    @NotBlank(message="text cannot be empity")
    private String text;

    private Integer orderSlide;

    @NotNull(message = "organizationId cannot be null")
    private Long organizationId;

}
