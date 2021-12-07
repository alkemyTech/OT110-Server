
package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryByNameDto;
import com.alkemy.ong.dto.CategoryDto;


import com.alkemy.ong.dto.CategoryRequestUpdate;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.ICategoryService;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryController {
	
    @Autowired
    private ICategoryService categoryService;
    
    @GetMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<?> findById(@Valid @PathVariable("id") Long id ) {
        try {
		CategoryDto categoryDto = categoryService.findById(id);
                if (categoryDto == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(categoryDto, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
		}
    }

    @GetMapping()
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<List<CategoryByNameDto>> findCategoriesByName(){
            List<CategoryByNameDto> listCategories = categoryService.findByName();
            return new ResponseEntity<List<CategoryByNameDto>>(listCategories, HttpStatus.OK);
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