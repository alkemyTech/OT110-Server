
package com.alkemy.ong.dto;

import javax.validation.constraints.NotBlank;

import com.alkemy.ong.util.ImageExtension;
import com.alkemy.ong.util.docs.CategoryConstantDocs;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.URL;
import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryRequestUpdate {

	@NotBlank(message = "Name Category is necessary.")
	@ApiModelProperty(value = CategoryConstantDocs.CATEGORY_CATEGORYREQUESTUPDATE_NAME)
	private String name;
	@Nullable
	@ApiModelProperty(value = CategoryConstantDocs.CATEGORY_CATEGORYREQUESTUPDATE_DESCRIPTION)
	private String description;
	@Nullable
	@ApiModelProperty(value = CategoryConstantDocs.CATEGORY_CATEGORYREQUESTUPDATE_IMAGE)
	@URL(message = "Image field must be a valid url")
	@ImageExtension(message = "image extension not valid, must be JPG, JPEG or PNG")
	private String image;

}
