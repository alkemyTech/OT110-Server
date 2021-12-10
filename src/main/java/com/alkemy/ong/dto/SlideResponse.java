package com.alkemy.ong.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SlideResponse {

    private Long id;
    private String base64Image;
    private String text;
    private Long organizationId;
    private Integer orderSlide;
}
