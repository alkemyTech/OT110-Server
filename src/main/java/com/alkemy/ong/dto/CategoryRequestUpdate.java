
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
public class CategoryRequestUpdate {

	@NotNull(message = "Name Category is necessary.")
	@ApiModelProperty(value = CategoryConstantDocs.CATEGORY_CATEGORYREQUESTUPDATE_NAME)
	private String name;
	@Nullable
	@ApiModelProperty(value = CategoryConstantDocs.CATEGORY_CATEGORYREQUESTUPDATE_DESCRIPTION)
	private String description;
	@Nullable
	@ApiModelProperty(value = CategoryConstantDocs.CATEGORY_CATEGORYREQUESTUPDATE_IMAGE)
	private String image;

}
