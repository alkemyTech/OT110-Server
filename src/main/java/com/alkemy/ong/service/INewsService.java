
package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsRequest;
import com.alkemy.ong.dto.NewsResponse;
import org.springframework.http.ResponseEntity;

public interface INewsService {

    NewsResponse findById(Long id);

    NewsResponse updateNewsById(Long id, NewsRequest news);

    ResponseEntity<?> delete(Long id);

}
