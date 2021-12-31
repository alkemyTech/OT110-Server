package com.alkemy.ong.dto;

import com.alkemy.ong.util.ImageExtension;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ActivityRequest {

    @NotBlank(message = "Name field is necessary")
    private String name;
    @NotBlank(message = "Content field is necessary")
    private String content;
    @URL(message = "Image field must be a valid url")
    @ImageExtension(message = "image extension not valid, must be JPG, JPEG or PNG")
    private String image;

}
