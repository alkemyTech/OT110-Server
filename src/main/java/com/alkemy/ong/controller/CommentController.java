package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentResponse;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {

    private final ICommentService commentService;

    @GetMapping("/posts/{id}/comments")
    @PreAuthorize(SecurityConstant.USER)
    public ResponseEntity<List<CommentResponse>> findAll(@Valid @PathVariable("id") Long id){
        return new ResponseEntity<>(commentService.getAllComments(id), HttpStatus.OK);
    }

}
