package com.alkemy.ong.integration.category;


import com.alkemy.ong.dto.CategoryRequestUpdate;
import com.alkemy.ong.dto.TestimonialResponse;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.security.RoleEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateCategoryControllerTest extends BaseCategoryTest{

    private final Long ID2UPDATE = generateCategory().getId();
    private final String PATH = "/category/" + ID2UPDATE;

    @Test
    public void ReturnUnauthorizedIfUserIsNotADMIN() {
        login(RoleEnum.USER.getRoleName());
        CategoryRequestUpdate categoryRequestUpdate = generateCategoryRequestUpdate();

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.PUT, new HttpEntity<>(categoryRequestUpdate,headers), Object.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void ReturnBadRequestIfAnyAttributeIsNull() {
        login(RoleEnum.ADMIN.getRoleName());

        CategoryRequestUpdate categoryRequestUpdate = generateCategoryRequestUpdate();
        categoryRequestUpdate.setName(null);

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.PUT, new HttpEntity<>(categoryRequestUpdate, headers), Object.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void ReturnNotFoundIfIdNotExist() {
        when(categoryRepository.findById(eq(ID2UPDATE))).thenReturn(Optional.empty());

        login(RoleEnum.ADMIN.getRoleName());

        CategoryRequestUpdate categoryRequestUpdate = generateCategoryRequestUpdate();

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.PUT, new HttpEntity<>(categoryRequestUpdate, headers), Object.class);

        assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);

    }


    @Test
    public void UpdateCategorySuccess() {
        Category categoryModified = generateCategory();
        categoryModified.setId(1L);
        categoryModified.setName("Modified");
        categoryModified.setDescription("Modified");
        categoryModified.setImage("modified.jpg");

        when(categoryRepository.findById(ID2UPDATE)).thenReturn(Optional.of(generateCategory()));

        when(categoryRepository.save(isA(Category.class))).thenReturn(categoryModified);

        login(RoleEnum.ADMIN.getRoleName());

        CategoryRequestUpdate categoryRequestUpdate = generateCategoryRequestUpdate();

        categoryRequestUpdate.setName("Modified");
        categoryRequestUpdate.setDescription("Modified");

        ResponseEntity<TestimonialResponse> response = testRestTemplate.exchange(
                createURLWithPort(PATH), HttpMethod.PUT, new HttpEntity<>(categoryRequestUpdate, headers), TestimonialResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

}
