package com.netcracker.converter;


import com.netcracker.dto.CommentDto;
import com.netcracker.entity.Comments;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public Comments converter(CommentDto commentDto) {

        Comments comments = new Comments();

        comments.setComment(commentDto.getComment());

        return comments;
    }

    public CommentDto converter(Comments comments) {
        CommentDto commentDto = new CommentDto();

        commentDto.setId(comments.getId());

        commentDto.setComment(comments.getComment());

        commentDto.setApartmentId(comments.getApartment().getId());

        //commentDto.setPersonLogin(comments.getPerson().getLogin());

        return commentDto;
    }

}
