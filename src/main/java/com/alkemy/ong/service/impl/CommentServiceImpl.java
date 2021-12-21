package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentRequest;
import com.alkemy.ong.dto.CommentResponse;
import com.alkemy.ong.exception.EmptyDataException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

    private final CommentRepository commentRepository;
    private final UserServiceImpl userService;
    private final NewsRepository newsRepository;
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
        String newsNotFound = messageSource.getMessage("news.notFound",null, Locale.US);

        Optional<News> existNews = newsRepository.findById(id);
        List<CommentResponse> comments = new ArrayList<>();

        if(existNews.isPresent()){
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
        } else{
            throw new NotFoundException(newsNotFound);
        }
        return comments;
    }
}
