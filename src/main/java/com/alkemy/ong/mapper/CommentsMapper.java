package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CommentResponseList;
import com.alkemy.ong.model.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CommentsMapper {

    public CommentResponseList commentModel2DTO(Comment model){
        CommentResponseList dto = new CommentResponseList();
        dto.setBody(model.getBody());

        return dto;
    }

    public List<CommentResponseList> commentModelList2DTOList(List<Comment> entities){
        List<CommentResponseList> dtos = new ArrayList<>();
        for (Comment entity : entities) {
            dtos.add(commentModel2DTO(entity));
        }
        return dtos;
    }
}
