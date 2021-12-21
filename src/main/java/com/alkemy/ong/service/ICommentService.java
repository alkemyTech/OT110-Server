package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentRequest;
import com.alkemy.ong.dto.CommentResponse;
import com.alkemy.ong.dto.CommentResponseList;

import java.util.List;

public interface ICommentService {

    CommentRequest addComment(CommentRequest commentRequest);
    CommentResponse updateCommentsById(Long id, CommentRequest commentRequest, String authorizationHeader);
    List<CommentResponseList> getAll();
    List<CommentResponse> getAllComments(Long id);

}
