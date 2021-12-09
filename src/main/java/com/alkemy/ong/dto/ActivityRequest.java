package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class ActivityRequest {

    @NotBlank(message = "Name field is necessary")
    private String name;
    @NotBlank(message = "Content field is necessary")
    private String content;
    @URL(message = "Image field must be a valid url")
    private String image;
}
