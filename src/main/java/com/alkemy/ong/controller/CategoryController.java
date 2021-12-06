
package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.service.ICategoryService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/category")
public class CategoryController {
     
    @Autowired
    private ICategoryService iCategoryService;
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole(T(com.alkemy.ong.security.RoleEnum).ADMIN)")
    public ResponseEntity<?> findById(@Valid @PathVariable("id") Long id ) {
        try {
		CategoryDto categoryDto = iCategoryService.findById(id);
                if (categoryDto == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(categoryDto, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
		}
    }
    
    
}