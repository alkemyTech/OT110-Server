
package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryByNameDto;
import com.alkemy.ong.dto.CategoryRequestUpdate;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;
    
    @GetMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<?> findById(@Valid @PathVariable("id") Long id ){
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<List<CategoryByNameDto>> findAllCategoriesByName(){
            List<CategoryByNameDto> listCategories = categoryService.findAllByName();
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