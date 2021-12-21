package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentRequest;
import com.alkemy.ong.dto.CommentResponse;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.dto.CommentResponseList;
import com.alkemy.ong.exception.EmptyDataException;
import com.alkemy.ong.mapper.CommentsMapper;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.security.RoleEnum;
import com.alkemy.ong.security.jwt.JwtProviderImpl;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.util.UpdateFields;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

    private final CommentRepository commentRepository;
    private final CommentsMapper commentsMapper;
    private final UserServiceImpl userService;
    private final NewsServiceImpl newsService;
    private final MessageSource messageSource;
    private final UpdateFields updateFields;

    private final JwtProviderImpl jwUtil;

    @Override
    public CommentRequest addComment(CommentRequest commentRequestDto) {
        Comment comment = commentDTOEntity(commentRequestDto);
        Comment commentSave = commentRepository.save(comment);
        CommentRequest result = commentEntity2DTO(commentSave);

        return result;

    }
    
    public Comment commentDTOEntity(CommentRequest commentDto){
        Comment comment= new Comment();
        User user = userService.findById(commentDto.getUserId());
        News news = newsService.findByIdReturnNews(commentDto.getNewsId());
        
        comment.setNews(news);
        comment.setUser(user);
        comment.setBody(commentDto.getBody());
        comment.setDateUpdate(LocalDateTime.now());
        return comment;
    }
    
    public CommentRequest commentEntity2DTO(Comment comment){
        CommentRequest commentDto= new CommentRequest();
        
        commentDto.setBody(comment.getBody());
        commentDto.setUserId(comment.getUser().getUserId());
        commentDto.setNewsId(comment.getNews().getId());

        return commentDto;
    }

    @Override
    public CommentResponse updateCommentsById(Long id, CommentRequest commentRequest, String authorizationHeader) {
        String commentNotFound = messageSource.getMessage("comment.notFound", null, Locale.US);
        String notFoundUserMessage = messageSource.getMessage("user.notFound", null, Locale.US);
        String deniedUser = messageSource.getMessage("user.isNotAllowed", null, Locale.US);

        Comment commentToUpdate = commentRepository.findById(id).orElseThrow(() -> new NotFoundException(commentNotFound));

        User user = userService.findByEmail(jwUtil.extractUsername(authorizationHeader)).orElseThrow(() -> new NotFoundException(notFoundUserMessage));

        if (!commentRequest.getUserId().equals(user.getUserId()) && !RoleEnum.ADMIN.getName().equals(user.getRole().getName()))
            throw new BadCredentialsException(deniedUser);

        updateFields.updateIfNotBlankAndNotEqual(commentRequest.getBody(), commentToUpdate.getBody(), commentToUpdate::setBody, "body");
        commentRepository.save(commentToUpdate);

        return new ModelMapper()
                .typeMap(Comment.class, CommentResponse.class)
                .map(commentToUpdate);
    }

    public List<CommentResponseList> getAll() {
        String commentListIsEmpty = messageSource.getMessage("comments.listEmpty", null, Locale.US);
        if(commentRepository.findAll().isEmpty()){
            throw new EmptyDataException(commentListIsEmpty);
        }
        List<Comment> entities = commentRepository.findAll();
        entities.sort(Comparator.comparing(o -> o.getDateCreation()));
        List<CommentResponseList> result = commentsMapper.commentModelList2DTOList(entities);
        return result;
    }
}
