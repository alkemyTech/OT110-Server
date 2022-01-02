package com.alkemy.ong.controller;


import com.alkemy.ong.dto.CommentRequest;
import com.alkemy.ong.dto.CommentResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.alkemy.ong.dto.CommentResponseList;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.ICommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {


    private final ICommentService commentService;

    @GetMapping("/posts/{id}/comments")
    @PreAuthorize(SecurityConstant.USER)
    public ResponseEntity<List<CommentResponse>> findAll(@Valid @PathVariable("id") Long id) {
        return new ResponseEntity<>(commentService.getAllComments(id), HttpStatus.OK);
    }
    
    @PostMapping
    @PreAuthorize(SecurityConstant.USER)
    public ResponseEntity<CommentRequest> createComment(@Valid @RequestBody CommentRequest comment) throws Exception {
        CommentRequest comentRequest = commentService.addComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize(SecurityConstant.USER_ADMIN)
    public ResponseEntity<CommentResponse> updateCommentsById(@Valid @RequestBody CommentRequest comment, @PathVariable("id") Long id, @RequestHeader(value = "Authorization") String authorizationHeader) {
        return new ResponseEntity<>(commentService.updateCommentsById(id, comment, authorizationHeader), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<List<CommentResponseList>> getAll() {
        List<CommentResponseList> comments = commentService.getAll();
        return ResponseEntity.ok().body(comments);

    }
    
    	@DeleteMapping("/{id}")
	    @PreAuthorize(SecurityConstant.USER_ADMIN) 
	    public ResponseEntity<?> delete(@Valid @PathVariable("id") Long id) {

		  return commentService.delete(id);

	}

}

