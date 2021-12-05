/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.ICategoryService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author mateo
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService{
    
    public  final CategoryRepository categoryRepository;
    
    
    @Override
    public CategoryDto findById(Long id){
        Optional<Category> rta= categoryRepository.findById(id);
        CategoryDto categoryDto= new CategoryDto();
        if (rta.isPresent()) {
            Category category= rta.get();
            categoryDto.setId(category.getId());
            categoryDto.setImage(category.getImage());
            categoryDto.setDescripcion(category.getDescription());
            categoryDto.setName(category.getName());
            categoryDto.setDateCreacion(category.getDateCreation());
            categoryDto.setDateUpdate(category.getDateUpdate());
        }
        return categoryDto;
    }
}
