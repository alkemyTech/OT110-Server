
package com.alkemy.ong.service;

import org.springframework.http.ResponseEntity;

import com.alkemy.ong.dto.NewsRequest;
import com.alkemy.ong.dto.NewsResponse;

public interface INewsService {

    NewsResponse findById(Long id);

    NewsResponse updateNewsById(Long id, NewsRequest news);

    ResponseEntity<?> delete(Long id);

}
