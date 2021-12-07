package com.alkemy.ong.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuthenticatedResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
}
