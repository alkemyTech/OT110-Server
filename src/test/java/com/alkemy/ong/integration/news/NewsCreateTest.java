package com.alkemy.ong.integration.news;

import com.alkemy.ong.common.BaseNewsTest;
import com.alkemy.ong.dto.NewsRequest;
import com.alkemy.ong.dto.NewsResponse;
import com.alkemy.ong.model.News;
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

import static org.mockito.ArgumentMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsCreateTest extends BaseNewsTest {

    private final String PATH = "/news";
    private NewsRequest newsRequest = new NewsRequest();

    @Before
    public void setUp(){
        newsRequest= createNewsRequest();
    }

    @Test
    public void ReturnUnauthorizedIfUserIsNotAdmin() {

        login(RoleEnum.USER.getRoleName());
        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.POST, new HttpEntity<>(newsRequest, headers), Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Before
    public void AdminLogin() {
        login(RoleEnum.ADMIN.getRoleName());
    }

    @Test
    public void ReturnBadRequestIfAnyAttributeIsNull() {
        newsRequest.setName("");
        newsRequest.setContent("");

        ResponseEntity<NewsResponse> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.POST, new HttpEntity<>(newsRequest, headers), NewsResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void createNewsSuccessfully(){

        when(newsRepository.save(isA(News.class))).thenReturn(generateNews());

        ResponseEntity<NewsResponse> response = testRestTemplate.exchange(
                createURLWithPort(PATH), HttpMethod.POST, new HttpEntity<>(newsRequest, headers), NewsResponse.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}

