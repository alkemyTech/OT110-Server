/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.ong.service;


import com.alkemy.ong.dto.CategoryByNameDto;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryRequestUpdate;




import java.util.List;


public interface ICategoryService {


    public List<CategoryByNameDto> findByName();

    public CategoryDto createCategory(@Valid CategoryRequestUpdate category);

    public ResponseEntity<?> delete(Long id);

    CategoryDto findById(Long id);
    
	CategoryDto updateCategory(@Valid CategoryRequestUpdate category, Long id);


}
