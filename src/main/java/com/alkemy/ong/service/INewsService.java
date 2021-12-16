
package com.alkemy.ong.service;

import org.springframework.http.ResponseEntity;

import com.alkemy.ong.dto.NewsRequest;
import com.alkemy.ong.dto.NewsResponse;
import com.alkemy.ong.model.News;

public interface INewsService {

    NewsRequest createNews(NewsRequest news) throws Exception;

    NewsResponse findById(Long id);

    NewsResponse updateNewsById(Long id, NewsRequest news);

    ResponseEntity<?> delete(Long id);

    News findByIdReturnNews(Long id);

}
