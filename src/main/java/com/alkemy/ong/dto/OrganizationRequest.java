package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.openapitools.jackson.nullable.JsonNullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class OrganizationRequest {


    private String name;
    private String image;
    private JsonNullable<String> address;
    @Max(value = 9999999999999L, message = "Invalid phone number")
    @Min(value = 0, message = "Invalid phone number")
    private JsonNullable<Long> phone;
    @Pattern(regexp=".+@.+\\.[a-z]+", message="Invalid email address")
    private String email;
    private String welcomeText;
    private JsonNullable<String> aboutUsText;

}
