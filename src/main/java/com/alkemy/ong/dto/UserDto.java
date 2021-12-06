package com.alkemy.ong.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
}

