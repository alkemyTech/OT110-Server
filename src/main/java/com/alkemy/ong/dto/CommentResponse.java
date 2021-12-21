package com.alkemy.ong.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    private Long id;
    private Long userId;
    private String body;
    private Long newsId;
}

