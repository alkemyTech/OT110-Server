/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryByNameDto;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.ICategoryService;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 *
 * @author mateo
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService{
    
    public  final CategoryRepository categoryRepository;
    private final MessageSource messageSource;
    
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

    @Override
    public List<CategoryByNameDto> findByName() {

        String categoryListIsEmpty = messageSource.getMessage("category.listEmpty", null, Locale.US);

        List<CategoryByNameDto> categoryByNameDto = categoryRepository.findAll()
                .stream()
                .map(name -> mapCategoryToCategoryDto(name))
                .collect(Collectors.toList());
        if(categoryByNameDto.isEmpty()){
            throw new NotFoundException(categoryListIsEmpty);
        }
        return categoryByNameDto;
    }

    private CategoryByNameDto mapCategoryToCategoryDto(Category category){
        String name = category.getName();
        return new CategoryByNameDto(name);
    }
}

