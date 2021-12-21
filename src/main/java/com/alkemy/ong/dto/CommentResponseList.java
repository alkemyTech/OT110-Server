package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentResponseList {

    @NotBlank(message = "body cannot be blank")
    private String body;
}
