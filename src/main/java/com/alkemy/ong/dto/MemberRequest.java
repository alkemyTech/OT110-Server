package com.alkemy.ong.dto;

import com.alkemy.ong.util.ImageExtension;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.openapitools.jackson.nullable.JsonNullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

    @NotBlank(message = "Name field is necessary")
    @Pattern(regexp = "^[A-Z]'?[- a-zA-Z]*$",message = "Name field must be a text string")
    private String name;
    @URL(message = "Must be a valid URL")
    private JsonNullable<String> facebookUrl;
    @URL(message = "Must be a valid URL")
    private JsonNullable<String> instagramUrl;
    @URL(message = "Must be a valid URL")
    private JsonNullable<String> linkedinUrl;
    @NotNull(message = "image cannot be null")
    @URL(message = "Image field must be a valid url")
    @ImageExtension(message = "image extension not valid, must be JPG, JPEG or PNG")
    private String image;
    private String description;

}
