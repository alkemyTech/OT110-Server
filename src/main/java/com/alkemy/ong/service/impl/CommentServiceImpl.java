package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentRequest;
import com.alkemy.ong.dto.CommentResponse;
import com.alkemy.ong.exception.EmptyDataException;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

    private final CommentRepository commentRepository;
    private final UserServiceImpl userService;
    private final NewsServiceImpl newsService;
    private final MessageSource messageSource;

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

    @Override
    public List<CommentResponse> getAllComments(Long id) {
        String contactListEmpty = messageSource.getMessage("contact.listEmpty",null, Locale.US);

        List<CommentResponse> comments = new ArrayList<>();

        commentRepository.findAll()
                .stream()
                .filter(comment -> comment.getNews().getId() == id)
                .forEach(comment -> {
                    CommentResponse commentResponse = new CommentResponse();
                    commentResponse.setUserId(comment.getUser().getUserId());
                    commentResponse.setBody(comment.getBody());
                    commentResponse.setNewsId(comment.getNews().getId());
                    comments.add(commentResponse);
                });
        if(comments.isEmpty()){
            throw new EmptyDataException(contactListEmpty);
        }
        return comments;
    }
}
