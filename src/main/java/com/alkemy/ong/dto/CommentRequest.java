package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentRequest {

    @NotNull(message = "userId cannot be null")
    private Long userId;
    @NotBlank(message = "body cannot be blank")
    private String body;
    @NotNull(message = "newsId cannot be null")
    private Long newsId;

}
