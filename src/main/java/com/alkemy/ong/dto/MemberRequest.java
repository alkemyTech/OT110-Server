package com.alkemy.ong.dto;

import com.alkemy.ong.util.ImageExtension;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class MemberRequest {

    @NotBlank(message = "Name field is necessary")
    @Pattern(regexp = "^[A-Za-z]*$",message = "Name field must be a text string")
    private String name;
    @URL(message = "Must be a valid URL")
    private String facebookUrl;
    @URL(message = "Must be a valid URL")
    private String instagramUrl;
    @URL(message = "Must be a valid URL")
    private String linkedinUrl;
    @URL(message = "Image field must be a valid url")
    @ImageExtension(message = "image extension not valid, must be JPG, JPEG or PNG")
    private String image;
    private String description;

}

