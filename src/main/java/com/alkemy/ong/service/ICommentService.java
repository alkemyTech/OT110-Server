package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentRequest;
import com.alkemy.ong.dto.CommentResponse;
import com.alkemy.ong.model.Comment;

import java.util.List;

public interface ICommentService {

    Comment addComment(CommentRequest commentRequest);
    List<CommentResponse> getAllComments(Long id);
}
