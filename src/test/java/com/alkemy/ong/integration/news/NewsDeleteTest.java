package com.alkemy.ong.integration.news;

import com.alkemy.ong.common.BaseNewsTest;
import com.alkemy.ong.security.RoleEnum;
import org.junit.Before;
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
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsDeleteTest extends BaseNewsTest {
    private final Long ID = generateNews().getId();
    private final String PATH = "/news/" + ID;

    @Test
    public void ReturnUnauthorizedIfUserIsNotADMIN() {
        login(RoleEnum.USER.getRoleName());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.DELETE, new HttpEntity<>(headers), Object.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Before
    public void AdminLogin() {
        login(RoleEnum.ADMIN.getRoleName());
    }

    @Test
    public void ReturnNotFoundIfIdNotExist() {
        when(newsRepository.findById(eq(ID))).thenReturn(Optional.empty());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.DELETE, new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void DeleteNewsSuccess() {
        when(newsRepository.findById(eq(ID)))
                .thenReturn(Optional.of(generateNews()));


        when(newsRepository.save(eq(generateNews())))
                .thenReturn(generateNews());


        ResponseEntity<?> response =
                testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.DELETE, new HttpEntity<>(headers), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
