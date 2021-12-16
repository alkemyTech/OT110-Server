package com.alkemy.ong.mapper;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.CategoryByNameDto;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryRequestUpdate;
import com.alkemy.ong.model.Category;

@Component
public class CategoryMapper {

    public CategoryByNameDto mapCategoryToCategoryDto(Category category){
        String name = category.getName();
        return new CategoryByNameDto(name);
    }
    
    public CategoryDto mapEntityToDto(Category categoryUpdated) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(categoryUpdated.getId());
		categoryDto.setName(categoryUpdated.getName());
		categoryDto.setDescription(categoryUpdated.getDescription());
		categoryDto.setImage(categoryUpdated.getImage());
		categoryDto.setDateCreation(categoryUpdated.getDateCreation().toString());
		categoryDto.setDateUpdate(categoryUpdated.getDateUpdate().toString());
		return categoryDto;
	}

    public void mapDtoToEntity(Category categoryEntity, @Valid CategoryRequestUpdate category) {
		categoryEntity.setName(category.getName());
		categoryEntity.setDescription(category.getDescription());
		categoryEntity.setImage(category.getImage());
		categoryEntity.setDateUpdate(LocalDateTime.now());
	}

    public void mapDtoToEntityWithDateOfCreation(Category categoryEntity, @Valid CategoryRequestUpdate category) {
		categoryEntity.setName(category.getName());
		categoryEntity.setDescription(category.getDescription());
		categoryEntity.setImage(category.getImage());
		categoryEntity.setDateCreation(LocalDateTime.now());
		categoryEntity.setDateUpdate(LocalDateTime.now());
	}	
	
}
