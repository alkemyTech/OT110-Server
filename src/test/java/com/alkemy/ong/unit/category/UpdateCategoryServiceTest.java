package com.alkemy.ong.unit.category;


import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryServiceTest extends BaseCategoryServiceTest{


    @BeforeEach
    void setUp(){
        categoryMapper = new CategoryMapper();
        underTest = new CategoryServiceImpl(categoryRepository, categoryMapper, messageSource);

        generateMockCategoryUpdate();

        mockCategory = new Category();
        mockCategory.setId(ID_CATEGORY);
        categoryMapper.mapDtoToEntityWithDateOfCreation(mockCategory, mockCategoryUpdate);
    }

    @Test
    void canUpdateCategory(){

        Optional<Category> mockOptionalCategory = Optional.of(mockCategory);
        given(categoryRepository.findById(ID_CATEGORY)).willReturn(mockOptionalCategory);
        given(categoryRepository.save(any(Category.class))).willReturn(mockCategory);

        Object categoryDto = underTest.updateCategory(mockCategoryUpdate, ID_CATEGORY);

        verify(categoryRepository).save(any(Category.class));
        assertThat(categoryDto).isExactlyInstanceOf(categoryDto.getClass());

    }

    @Test
    void checkIfCategoryDoesNotExist(){

        given(categoryRepository.findById(ID_CATEGORY_NOTFOUND)).willReturn(Optional.empty());

        assertThatThrownBy(()->underTest.updateCategory(mockCategoryUpdate,ID_CATEGORY_NOTFOUND))
                .isInstanceOf(NotFoundException.class);
        verify(categoryRepository).findById(ID_CATEGORY_NOTFOUND);
        verify(categoryRepository,never()).save(any(Category.class));

    }
}
