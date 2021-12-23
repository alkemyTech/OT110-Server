
package com.alkemy.ong.dto;

import com.alkemy.ong.util.docs.CategoryConstantDocs;
import io.swagger.annotations.ApiModelProperty;
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
public class CategoryDto {

    @ApiModelProperty(value = CategoryConstantDocs.CATEGORY_DTO_ID)
    private Long id;
    @ApiModelProperty(value = CategoryConstantDocs.CATEGORY_DTO_NAME)
    private String name;
    @ApiModelProperty(value = CategoryConstantDocs.CATEGORY_DTO_DESCRIPTION)
    private String description;
    @ApiModelProperty(value = CategoryConstantDocs.CATEGORY_DTO_IMAGE)
    private String image;
    @ApiModelProperty(value = CategoryConstantDocs.CATEGORY_DTO_DATE_CREATION)
    private String dateCreation;
    @ApiModelProperty(value = CategoryConstantDocs.CATEGORY_DTO_DATE_UPDATE)
    private String dateUpdate;

}
