package com.alkemy.ong.common;

import com.alkemy.ong.dto.NewsRequest;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.NewsRepository;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

public class BaseNewsTest extends BaseGeneralTest {

    @MockBean
    protected NewsRepository newsRepository;

    @MockBean
    protected CategoryRepository categoryRepository;

    protected Category generateCategory(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Politica");
        category.setDescription("Descripción de la categoria política");
        category.setImage("https://somosmasImageNoticia.jpg");
        category.setEnabled(Boolean.TRUE);
        return category;
    }

    protected News generateNews(){
        News news = new News();
        news.setId(1l);
        news.setName("Noticia");
        news.setContent("Esta es una noticia a tener en cuenta");
        news.setImage("https://somosmasImageNoticia.jpg");
        news.setCategory(generateCategory());
        news.setEnabled(Boolean.TRUE);
        return news;
    }

    protected NewsRequest createNewsRequest() {
        NewsRequest newsRequest = new NewsRequest();
        newsRequest.setName("Noticia sobre Politica");
        newsRequest.setContent("Esta es una noticia sobre politica que puede interesarle a mucha gente");
        newsRequest.setImage("https://somosmasImage.jpg");
        newsRequest.setCategoryId(generateCategory().getId());
        return newsRequest;
    }

    protected List<News> createListNews(int count) {
        List<News> news = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            news.add(generateNews());
        }
        return news;
    }

}

