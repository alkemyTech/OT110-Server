package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentRequest;
import com.alkemy.ong.dto.CommentResponseList;
import com.alkemy.ong.dto.ContactResponseDto;
import com.alkemy.ong.model.Comment;

import java.util.List;

public interface ICommentService {

    Comment addComment(CommentRequest commentRequest);
    List<CommentResponseList> getAll();

}
