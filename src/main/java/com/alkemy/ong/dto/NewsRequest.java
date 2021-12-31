package com.alkemy.ong.dto;

import com.alkemy.ong.util.ImageExtension;
import com.alkemy.ong.util.docs.NewsConstantsDocs;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewsRequest {

    @ApiModelProperty(value = NewsConstantsDocs.NEWS_DTOREQUEST_NAME)
    @NotBlank(message = "name cannot be null")
    private String name;

    @ApiModelProperty(value = NewsConstantsDocs.NEWS_DTOREQUEST_CONTENT)
    @NotBlank(message = "content cannot be null")
    private String content;

    @ApiModelProperty(value = NewsConstantsDocs.NEWS_DTOREQUEST_IMAGE)
    @NotNull(message = "image cannot be null")
    @URL(message = "url format not valid")
    @ImageExtension(message = "image extension not valid, must be JPG, JPEG or PNG")
    private String image;

    @ApiModelProperty(value = NewsConstantsDocs.NEWS_DTOREQUEST_CATEGORY)
    @NotNull(message = "categoryId cannot be null")
    private Long categoryId;

}
