package com.alkemy.ong.dto;

import com.alkemy.ong.controller.docs.TestimonialConstantDocs;
import com.alkemy.ong.util.ImageExtension;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Getter
@ApiModel(value = TestimonialConstantDocs.TESTIMONIAL_DTO_MODEL)
public class TestimonialRequest {

    @NotBlank(message = "name cannot be blank")
    @ApiModelProperty(notes = TestimonialConstantDocs.TESTIMONIAL_DTO_MODEL_FIELD_NAME)
    private String name;
    @URL(message = "image must be an URL")
    @ImageExtension(message = "image extension not valid, must be JPG, JPEG or PNG")
    @ApiModelProperty(notes = TestimonialConstantDocs.TESTIMONIAL_DTO_MODEL_FIELD_IMAGE)
    private String image;
    @NotBlank(message = "content cannot be blank")
    @ApiModelProperty(notes = TestimonialConstantDocs.TESTIMONIAL_DTO_MODEL_FIELD_CONTENT)
    private String content;
}
