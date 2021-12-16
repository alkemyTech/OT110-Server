package com.alkemy.ong.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

import com.alkemy.ong.controller.CategoryController;
import com.alkemy.ong.dto.CategoryByNameDto;
import com.alkemy.ong.model.Category;

@Component
public class CategoryAssembler extends RepresentationModelAssemblerSupport<Category, CategoryByNameDto>{

	public CategoryAssembler() {
		super(CategoryController.class, CategoryByNameDto.class);
	}

	@Override
	public CategoryByNameDto toModel(Category entity) {

		CategoryByNameDto categoryDto = instantiateModel(entity);
		
		categoryDto.add(linkTo(
				methodOn(CategoryController.class)
				.findById(entity.getId()))
				.withSelfRel());
		
		categoryDto.setName(entity.getName());
		
		return categoryDto;
	}

}
