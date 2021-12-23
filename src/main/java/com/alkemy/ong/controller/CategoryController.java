
package com.alkemy.ong.controller;

import com.alkemy.ong.assembler.CategoryAssembler;
import com.alkemy.ong.controller.docs.CategoryConstantDocs;
import com.alkemy.ong.dto.CategoryByNameDto;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryRequestUpdate;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.ICategoryService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/category")
@AllArgsConstructor
@Api(value = CategoryConstantDocs.CATEGORY)
public class CategoryController {

    private final ICategoryService categoryService;
    private final CategoryAssembler categoryAssembler;
    private final PagedResourcesAssembler<Category> pagedResourcesAssembler;
    
    @GetMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    @ApiOperation(value = CategoryConstantDocs.CATEGORY_FIND_BY_ID, response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = CategoryConstantDocs.CATEGORY_GET_200_OK),
            @ApiResponse(code = 404, message = CategoryConstantDocs.CATEGORY_404_NOT_FOUND)
    })
    public ResponseEntity<?> findById(
            @ApiParam(value = CategoryConstantDocs.CATEGORY_GET_PARAM_ID, required = true, example = "1")
            @Valid @PathVariable("id") Long id
    ){
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @GetMapping(params = "page")
    @PreAuthorize(SecurityConstant.USER_ADMIN)
    @ApiOperation(value = CategoryConstantDocs.CATEGORY_FIND_ALL_CATEGORIES_BY_NAME, response = PagedModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = CategoryConstantDocs.CATEGORY_GET_200_OK),
            @ApiResponse(code = 404, message = CategoryConstantDocs.CATEGORY_GET_404_NOT_FOUND)
    })
    public ResponseEntity<PagedModel<CategoryByNameDto>> findAllCategoriesByName(
            @ApiParam(required = false) Pageable pageable,
            @ApiParam(value = CategoryConstantDocs.CATEGORY_GET_PARAM_PAGE_NUMBER, required = true, example = "0")
            @RequestParam("page") int page
    ){
        Page<Category> categoryEntities = categoryService.readAllCategoriesByName(pageable, page);
        
        PagedModel<CategoryByNameDto> categoryDtoModel = pagedResourcesAssembler
                            .toModel(categoryEntities, categoryAssembler);
        return new ResponseEntity<>(categoryDtoModel,HttpStatus.OK);
    }

    
    @PostMapping()
    @PreAuthorize(SecurityConstant.ADMIN)
    @ApiOperation(value = CategoryConstantDocs.CATEGORY_CREATE, response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = CategoryConstantDocs.CATEGORY_POST_201_CREATED)
    })
    public ResponseEntity<?> createCategory(
            @ApiParam(value = CategoryConstantDocs.CATEGORY_POST_PARAM_CATEGORY_REQUEST, required = true)
            @Valid @RequestBody CategoryRequestUpdate category
    ) {
                return new ResponseEntity<>(categoryService.createCategory(category),HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    @ApiOperation(value = CategoryConstantDocs.CATEGORY_UPDATE, response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = CategoryConstantDocs.CATEGORY_PUT_200_OK),
            @ApiResponse(code = 403, message = CategoryConstantDocs.CATEGORY_PUT_403_FORBIDDEN),
            @ApiResponse(code = 404, message = CategoryConstantDocs.CATEGORY_404_NOT_FOUND)
    })
    public ResponseEntity<?> updateCategory(
            @ApiParam(value = CategoryConstantDocs.CATEGORY_PUT_PARAM_ID, required = true, example = "1")
            @PathVariable("id") Long id,
            @ApiParam(value = CategoryConstantDocs.CATEGORY_PUT_PARAM_CATEGORY_REQUEST, required = true)
            @Valid @RequestBody CategoryRequestUpdate category
    ) {
    	return new ResponseEntity<>(categoryService.updateCategory(category, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    @ApiOperation(value = CategoryConstantDocs.CATEGORY_DELETE, response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = CategoryConstantDocs.CATEGORY_DELETE_200_OK),
            @ApiResponse(code = 403, message = CategoryConstantDocs.CATEGORY_DELETE_403_FORBIDDEN),
            @ApiResponse(code = 404, message = CategoryConstantDocs.CATEGORY_404_NOT_FOUND)
    })
    public ResponseEntity<?> delete(
            @ApiParam(value = CategoryConstantDocs.CATEGORY_DELETE_PARAM_ID, required = true, example = "1")
            @Valid @PathVariable("id") Long id
    ) {
    	return categoryService.delete(id);
    }

}