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

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsUpdateTest extends BaseNewsTest {

    private final Long ID = generateNews().getId();
    private final String PATH = "/news/" + ID;
    private NewsRequest newsRequest = new NewsRequest();

    @Before
    public void setUp(){
        newsRequest= createNewsRequest();
    }

    @Test
    public void ReturnUnauthorizedIfUserIsNotADMIN() {
        login(RoleEnum.USER.getRoleName());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.PUT, new HttpEntity<>(newsRequest,headers), Object.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
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
                HttpMethod.PUT, new HttpEntity<>(newsRequest, headers), NewsResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void ReturnNotFoundIfIdNotExist() {
        when(newsRepository.findById(eq(ID))).thenReturn(Optional.empty());

        ResponseEntity<NewsRequest> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.PUT, new HttpEntity<>(newsRequest, headers), NewsRequest.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void UpdateNewsSuccess() {
        News newsModified = generateNews();
        newsModified.setId(1L);
        newsModified.setName("News Modified");
        newsModified.setContent("Content Modified");
        newsModified.setImage("https://somosmasImageNoticiaModified.jpg");

        when(newsRepository.findById(ID)).thenReturn(Optional.of(generateNews()));

        when(newsRepository.save(isA(News.class))).thenReturn(newsModified);

        newsRequest.setName("News Modified");
        newsRequest.setContent("Content Modified");

        ResponseEntity<NewsResponse> response = testRestTemplate.exchange(
                createURLWithPort(PATH), HttpMethod.PUT, new HttpEntity<>(newsRequest, headers), NewsResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}

