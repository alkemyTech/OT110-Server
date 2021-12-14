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
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;



@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final MessageSource messageSource;
    
    @Override
    public CategoryDto findById(Long id) throws NotFoundException{
		String notFoundCategoryMessage = messageSource.getMessage("category.notFound", null, Locale.US);
        Category category = categoryRepository.findById(id).orElseThrow(()-> new NotFoundException(notFoundCategoryMessage));
		return categoryMapper.mapEntityToDto(category);
    }
    
    @Override
	public CategoryDto createCategory(@Valid CategoryRequestUpdate category) {
		Category categoryEntity = new Category();
		categoryMapper.mapDtoToEntityWithDateOfCreation(categoryEntity, category);
		Category categoryCreate = categoryRepository.save(categoryEntity);
		return categoryMapper.mapEntityToDto(categoryCreate);
	}

	@Override
	public CategoryDto updateCategory(@Valid CategoryRequestUpdate category, Long id) {
		Optional<Category> existCategory = categoryRepository.findById(id);
		if (existCategory.isPresent()) {
			Category categoryEntity = categoryRepository.findById(id).get();
			categoryMapper.mapDtoToEntity(categoryEntity, category);
			Category categoryUpdated = categoryRepository.save(categoryEntity);
			return categoryMapper.mapEntityToDto(categoryUpdated);
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
    public List<CategoryByNameDto> findAllByName() {

        String categoryListIsEmpty = messageSource.getMessage("category.listEmpty", null, Locale.US);

        List<CategoryByNameDto> categoryByNameDto = categoryRepository.findAll()
                .stream()
                .map(category -> categoryMapper.mapCategoryToCategoryDto(category))
                .collect(Collectors.toList());
        if(categoryByNameDto.isEmpty()){
            throw new NotFoundException(categoryListIsEmpty);
        }
        return categoryByNameDto;
    }

}

