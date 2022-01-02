package com.alkemy.ong.dto;

import com.alkemy.ong.util.ImageExtension;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class UserUpdateDto {

    private Long userId;
    private String firstName;
    private String lastName;
    @Email(message = "Email should be valid.", regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
    private String email;
    @URL(message = "Image field must be a valid url")
    @ImageExtension(message = "image extension not valid, must be JPG, JPEG or PNG")
    private String photo;
}