package com.alkemy.ong.dto;


import java.time.LocalDateTime;

import com.alkemy.ong.util.ImageExtension;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
