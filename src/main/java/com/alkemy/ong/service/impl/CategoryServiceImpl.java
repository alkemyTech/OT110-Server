/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryRequestUpdate;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.ICategoryService;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mateo
 */
@Service
public class CategoryServiceImpl implements ICategoryService{
    
	@Autowired
    private CategoryRepository categoryRepository;
    
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
            categoryDto.setDateCreacion(category.getDateCreation().toString());
            categoryDto.setDateUpdate(category.getDateUpdate().toString());
        }
        return categoryDto;
    }


	@Override
	public CategoryDto updateCategory(@Valid CategoryRequestUpdate category, Long id) {
		Optional<Category> existCategory = categoryRepository.findById(id);
		if (existCategory.isPresent()) {
			Category categoryEntity = categoryRepository.findById(id).get();
			mapDtoToEntity(categoryEntity, category);
			Category categoryUpdated = categoryRepository.save(categoryEntity);
			return mapEntityToDto(categoryUpdated);
		}
		return null;
	}


	private CategoryDto mapEntityToDto(Category categoryUpdated) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(categoryUpdated.getId());
		categoryDto.setName(categoryUpdated.getName());
		categoryDto.setDescripcion(categoryUpdated.getDescription());
		categoryDto.setImage(categoryUpdated.getImage());
		categoryDto.setDateCreacion(categoryUpdated.getDateCreation().toString());
		categoryDto.setDateUpdate(categoryUpdated.getDateUpdate().toString());
		return categoryDto;
	}


	private void mapDtoToEntity(Category categoryEntity, @Valid CategoryRequestUpdate category) {
		categoryEntity.setName(category.getName());
		categoryEntity.setDescription(category.getDescripcion());
		categoryEntity.setImage(category.getImage());
		categoryEntity.setDateUpdate(LocalDateTime.now());
	}
}
