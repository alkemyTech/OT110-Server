package com.alkemy.ong.integration.news;

import com.alkemy.ong.common.BaseNewsTest;
import com.alkemy.ong.model.News;
import com.alkemy.ong.security.RoleEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsListTest extends BaseNewsTest {

    private final Long ID2GET =generateNews().getId();
    private final String ONLY_PATH = "/news/" + ID2GET;
    private final String PATH = "/news?page=";
    private static final int SIZE_DEFAULT = 10;

    private List<News> news = new ArrayList<>();

    @Test
    public void ReturnForbiddenIfUserIsNotLogged() {
        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.GET,
                new HttpEntity<>(headers), Object.class);
        assertEquals( HttpStatus.FORBIDDEN, response.getStatusCode());
    }


    @Test
    public void ReturnOkIfListNewsIsEmpty() {
        int page = 10;
        Pageable pageable = PageRequest.of(page, SIZE_DEFAULT);
        news = createListNews(SIZE_DEFAULT);
        Page<News> pagedNews = new PageImpl<>(news);

        when(newsRepository.findAll(eq(pageable))).thenReturn(pagedNews);

        login(RoleEnum.USER.getRoleName());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH + page),
                HttpMethod.GET, new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }


    @Test
    public void ReturnNotFoundIfPageOutOfRange() {
        int page = 15;
        Pageable pageable = PageRequest.of(page, SIZE_DEFAULT);
        news = createListNews(SIZE_DEFAULT);
        Page<News> pagedNews = new PageImpl<>(news);

        when(newsRepository.findAll()).thenReturn(news);

        when(newsRepository.findAll(eq(pageable))).thenReturn(pagedNews);

        login(RoleEnum.USER.getRoleName());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH + page),
                HttpMethod.GET, new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void ListNewsByIdSuccess() {
        when(newsRepository.findById(eq(ID2GET)))
                .thenReturn(Optional.of(generateNews()));

        login(RoleEnum.ADMIN.getRoleName());

        ResponseEntity<Object> response =
                testRestTemplate.exchange(createURLWithPort(ONLY_PATH), HttpMethod.GET,
                        new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void ListNewsSuccess() {
        int page = 1;
        Pageable pageable = PageRequest.of(page, SIZE_DEFAULT);
        news = createListNews(SIZE_DEFAULT * 2);
        Page<News> pagedNews = new PageImpl<>(news, pageable, SIZE_DEFAULT * 2);

        when(newsRepository.findAll(eq(pageable))).thenReturn(pagedNews);

        login(RoleEnum.USER.getRoleName());

        ResponseEntity<Object> response =
                testRestTemplate.exchange(createURLWithPort(PATH + page), HttpMethod.GET,
                        new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

