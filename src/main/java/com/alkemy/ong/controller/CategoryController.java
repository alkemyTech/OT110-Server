
package com.alkemy.ong.controller;

import com.alkemy.ong.assembler.CategoryAssembler;
import com.alkemy.ong.dto.CategoryByNameDto;
import com.alkemy.ong.dto.CategoryRequestUpdate;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.ICategoryService;
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
public class CategoryController {

    private final ICategoryService categoryService;
    private final CategoryAssembler categoryAssembler;
    private final PagedResourcesAssembler<Category> pagedResourcesAssembler;
    
    @GetMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<?> findById(@Valid @PathVariable("id") Long id ){
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @GetMapping(params = "page")
    @PreAuthorize(SecurityConstant.USER_ADMIN)
    public ResponseEntity<PagedModel<CategoryByNameDto>> findAllCategoriesByName(Pageable pageable, @RequestParam("page") int page){
        Page<Category> categoryEntities = categoryService.readAllCategoriesByName(pageable, page);
        
        PagedModel<CategoryByNameDto> categoryDtoModel = pagedResourcesAssembler
                            .toModel(categoryEntities, categoryAssembler);
        return new ResponseEntity<>(categoryDtoModel,HttpStatus.OK);
    }

    
    @PostMapping()
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryRequestUpdate category) {
        try {
                return new ResponseEntity<>(categoryService.createCategory(category),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
		}
    }


    @PutMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryRequestUpdate category, @PathVariable("id") Long id){
    	return new ResponseEntity<>(categoryService.updateCategory(category, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<?> delete(@Valid @PathVariable("id") Long id ) {
       
    	return categoryService.delete(id);
		
    }

}