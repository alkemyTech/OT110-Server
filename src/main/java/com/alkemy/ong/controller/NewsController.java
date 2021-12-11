
    
   
package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsRequest;
import com.alkemy.ong.dto.NewsResponse;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.INewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {

    private final INewsService newsService;

    @PutMapping(path = "{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<NewsResponse> updateNewsById(@PathVariable("id") Long id, @Valid @RequestBody NewsRequest news){
        return new ResponseEntity<>(newsService.updateNewsById(id, news), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<?> findById(@Valid @PathVariable("id") Long id) {

        return new ResponseEntity<>(newsService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<NewsRequest> createNews(@Valid @RequestBody NewsRequest news) throws Exception {
        NewsRequest newsSaved = newsService.createNews(news);
        return ResponseEntity.status(HttpStatus.CREATED).body(newsSaved);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<?> delete(@Valid @PathVariable("id") Long id ) {
       
    	return newsService.delete(id);
		
    }


}
