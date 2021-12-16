package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentRequest;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

    private final CommentRepository commentRepository;
    private final UserServiceImpl userService;
    private final NewsServiceImpl newsService;

    @Override
    public Comment addComment(CommentRequest commentRequest) {

        Comment comment = new Comment();
        User user = userService.findById(commentRequest.getUserId());
        News news = newsService.findByIdReturnNews(commentRequest.getNewsId());

        comment.setUser(user);
        comment.setNews(news);
        comment.setBody(commentRequest.getBody());

        return commentRepository.save(comment);

    }
}
