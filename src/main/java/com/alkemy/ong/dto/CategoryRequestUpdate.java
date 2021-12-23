
package com.alkemy.ong.dto;

import javax.validation.constraints.NotNull;

import com.alkemy.ong.controller.docs.CategoryConstantDocs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel(value = CategoryConstantDocs.CATEGORY_DTO_MODEL)
public class CategoryRequestUpdate {

	@NotNull(message = "Name Category is necessary.")
	@ApiModelProperty(value = CategoryConstantDocs.CATEGORY_DTO_MODEL_FIELD_NAME)
	private String name;
	@Nullable
	@ApiModelProperty(value = CategoryConstantDocs.CATEGORY_DTO_MODEL_FIELD_DESCRIPTION)
	private String description;
	@Nullable
	@ApiModelProperty(value = CategoryConstantDocs.CATEGORY_DTO_MODEL_FIELD_IMAGE)
	private String image;

}
