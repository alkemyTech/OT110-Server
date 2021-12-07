/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryByNameDto;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryRequestUpdate;


import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.ICategoryService;


import java.util.List;
import java.util.Locale;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;




@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
    private CategoryRepository categoryRepository;

    private MessageSource messageSource;

    
    @Override
    public CategoryDto findById(Long id){
        Optional<Category> rta= categoryRepository.findById(id);
        if (rta.isPresent()) {
            Category category= rta.get();

            return mapEntityToDto(category);

        }
        return null;
    }

    
    @Override
	public CategoryDto createCategory(@Valid CategoryRequestUpdate category) {
		Category categoryEntity = new Category();
                mapDtoToEntity(categoryEntity, category);
                Category categoryCreate = categoryRepository.save(categoryEntity);
	        return mapEntityToDto(categoryCreate);
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
    
    @Override
    public ResponseEntity<?> delete(Long id) {
    	
    	String notFoundCategoryMessage = messageSource.getMessage("category.notFound", null, Locale.US);
    	String isDeletedCategoryMessage = messageSource.getMessage("category.isDeleted", null, Locale.US);
    	
    	Category category = categoryRepository.findById(id)
                 .orElseThrow(()-> new NotFoundException(notFoundCategoryMessage)); 
        categoryRepository.delete(category);
        return new ResponseEntity<>(isDeletedCategoryMessage, HttpStatus.OK);
       
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

    
	private CategoryDto mapEntityToDto(Category categoryUpdated) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(categoryUpdated.getId());
		categoryDto.setName(categoryUpdated.getName());
		categoryDto.setDescription(categoryUpdated.getDescription());
		categoryDto.setImage(categoryUpdated.getImage());
		categoryDto.setDateCreation(categoryUpdated.getDateCreation().toString());
		categoryDto.setDateUpdate(categoryUpdated.getDateUpdate().toString());
		return categoryDto;
	}


	private void mapDtoToEntity(Category categoryEntity, @Valid CategoryRequestUpdate category) {
		categoryEntity.setName(category.getName());
		categoryEntity.setDescription(category.getDescription());
		categoryEntity.setImage(category.getImage());
		categoryEntity.setDateUpdate(LocalDateTime.now());
	}    

}

