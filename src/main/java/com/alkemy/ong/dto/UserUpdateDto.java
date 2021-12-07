package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class UserUpdateDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private String password;
}