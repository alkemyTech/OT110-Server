
package com.alkemy.ong.dto;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryRequestUpdate {

	@NotNull(message = "Name Category is necessary.")
	private String name;
	@Nullable
	private String description;
	@Nullable
	private String image;

}
