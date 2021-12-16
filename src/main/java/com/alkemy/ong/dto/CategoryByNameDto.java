package com.alkemy.ong.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryByNameDto extends RepresentationModel<CategoryByNameDto>{
	private String name;
}
