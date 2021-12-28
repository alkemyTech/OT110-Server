package com.alkemy.ong.controller;

import com.alkemy.ong.assembler.NewsAssembler;
import com.alkemy.ong.dto.NewsRequest;
import com.alkemy.ong.dto.NewsResponse;
import com.alkemy.ong.model.News;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.INewsService;
import com.alkemy.ong.util.docs.NewsConstantsDocs;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
@Api(value = NewsConstantsDocs.NEWS)
public class NewsController {

    private final INewsService newsService;
    private final NewsAssembler newsAssembler;
    private final PagedResourcesAssembler<News> pagedResourcesAssembler;

    @PutMapping(path = "{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    @ApiOperation(value = NewsConstantsDocs.NEWS_UPDATE_BY_ID, response = NewsResponse.class)

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = NewsConstantsDocs.NEWS_UPDATE_BY_ID_OK),
            @ApiResponse(code = 404, message = NewsConstantsDocs.NEWS_404_NOT_FOUND)
    })
    public ResponseEntity<NewsResponse> updateNewsById(
            @ApiParam(value = NewsConstantsDocs.NEWS_UPDATE_PARAM_ID, required = true, example = "1")
            @PathVariable("id") Long id,
            @ApiParam(value = NewsConstantsDocs.NEWS_UPDATE_PARAM_NEWS_REQUEST, required = true)
            @Valid @RequestBody NewsRequest news){
        return new ResponseEntity<>(newsService.updateNewsById(id, news), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    @ApiOperation(value = NewsConstantsDocs.NEWS_FIND_BY_ID, response = NewsResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = NewsConstantsDocs.NEWS_GET_200_OK),
            @ApiResponse(code = 404, message = NewsConstantsDocs.NEWS_404_NOT_FOUND)
    })
    public ResponseEntity<?> findById(
            @ApiParam(value = NewsConstantsDocs.NEWS_GET_PARAM_ID, required = true, example = "1")
            @Valid @PathVariable("id") Long id) {

        return new ResponseEntity<>(newsService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize(SecurityConstant.ADMIN)
    @ApiOperation(value = NewsConstantsDocs.NEWS_CREATE, response = NewsResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = NewsConstantsDocs.NEWS_CREATED)
    })
    public ResponseEntity<NewsRequest> createNews(
            @ApiParam(value = NewsConstantsDocs.NEWS_CREATED_PARAM_NEW_REQUEST, required = true)
            @Valid @RequestBody NewsRequest news) throws Exception {
        NewsRequest newsSaved = newsService.createNews(news);
        return ResponseEntity.status(HttpStatus.CREATED).body(newsSaved);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    @ApiOperation(value = NewsConstantsDocs.NEWS_DELETE, response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = NewsConstantsDocs.NEWS_DELETE_OK),
            @ApiResponse(code = 404, message = NewsConstantsDocs.NEWS_404_NOT_FOUND)
    })
    public ResponseEntity<?> delete(
            @ApiParam(value = NewsConstantsDocs.NEWS_DELETE_PARAM_ID, required = true, example = "1")
            @Valid @PathVariable("id") Long id ) {
    	return newsService.delete(id);
		
    }

    @GetMapping(params = "page")
    @PreAuthorize(SecurityConstant.USER_ADMIN)
    @ApiOperation(value = NewsConstantsDocs.NEWS_FIND_ALL_NEWS_BY_NAME, response = PagedModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = NewsConstantsDocs.NEWS_GET_200_OK),
            @ApiResponse(code = 404, message = NewsConstantsDocs.NEWS_GET_404_NOT_FOUND)
    })
    public ResponseEntity<PagedModel<NewsResponse>> findAll(
            @ApiParam(required = false) Pageable pageable,
            @ApiParam(value = NewsConstantsDocs.NEWS_GET_PARAM_PAGE_NUMBER, required = true, example = "0")
            @RequestParam("page") int page
    ){
        Page<News> news = newsService.findAll(pageable, page);
        PagedModel<NewsResponse> newsResponse = pagedResourcesAssembler
                .toModel(news, newsAssembler);

        return new ResponseEntity<>(newsResponse, HttpStatus.OK);
    }

}
