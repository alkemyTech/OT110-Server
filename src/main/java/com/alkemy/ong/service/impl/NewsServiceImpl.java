

package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewsRequest;
import com.alkemy.ong.dto.NewsResponse;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.INewsService;
import com.alkemy.ong.util.UpdateFields;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements INewsService {

    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;
    private final MessageSource messageSource;
    private final UpdateFields updateFields;


    @Override
    public NewsResponse findById(Long id) {
        String newsNotFound = messageSource.getMessage("news.notFound", null, Locale.US);
        News news = newsRepository.findById(id).orElseThrow(() -> new NotFoundException(newsNotFound));


        NewsResponse newsResponse = new NewsResponse();
        newsResponse.setId(news.getId());
        newsResponse.setName(news.getName());
        newsResponse.setContent(news.getContent());
        newsResponse.setImage(news.getImage());
        newsResponse.setCategoryId(news.getCategory().getId());

        return newsResponse;
    }


    @Override
    public NewsResponse updateNewsById(Long id, NewsRequest news) {
        String newsNotFound = messageSource.getMessage("news.notFound", null, Locale.US);
        String categoryNotFound = messageSource.getMessage("category.notFound", null, Locale.US);

        News newsToUpdate = newsRepository.findById(id).orElseThrow(() -> new NotFoundException(newsNotFound));

        updateFields.updateIfNotBlankAndNotEqual(news.getName(), newsToUpdate.getName(), newsToUpdate::setName, "name");
        updateFields.updateIfNotBlankAndNotEqual(news.getContent(), newsToUpdate.getContent(), newsToUpdate::setContent, "content");
        updateFields.updateIfNotBlankAndNotEqual(news.getImage(), newsToUpdate.getImage(), newsToUpdate::setImage, "image");

        Category categoryToUpdate = newsToUpdate.getCategory();
        Category category = categoryRepository.findById(news.getCategoryId()).orElseThrow(() -> new NotFoundException(categoryNotFound));
        updateFields.updateIfNotBlankAndNotEqual(category, categoryToUpdate, newsToUpdate::setCategory, "category");

        if (updateFields.isHasUpdate()) {
            newsToUpdate.setDateUpdate(LocalDateTime.now());
        }

        return new ModelMapper()
                .typeMap(News.class, NewsResponse.class)
                .map(newsToUpdate);
    }
  
   @Override
    public ResponseEntity<?> delete(Long id) {
        String newsNotFound = messageSource.getMessage("news.notFound", null, Locale.US);
        String isDeletedNewsMessage = messageSource.getMessage("news.isDeleted", null, Locale.US);
        
        News news = newsRepository.findById(id).orElseThrow(()-> new NotFoundException(newsNotFound));
        newsRepository.delete(news);
        return new ResponseEntity<>(isDeletedNewsMessage, HttpStatus.OK);
    }
}


