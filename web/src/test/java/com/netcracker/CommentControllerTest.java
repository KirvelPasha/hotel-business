package com.netcracker;

import com.netcracker.controller.CommentController;
import com.netcracker.converter.CommentConverter;
import com.netcracker.dto.BookingDto;
import com.netcracker.dto.CommentDto;
import com.netcracker.entity.Comments;
import com.netcracker.repository.CommentRepository;
import com.netcracker.service.CommentService;
import com.netcracker.serviceimpl.CommentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CommentControllerTest {

    @Mock
    private CommentDto commentDto1;
    @Mock
    private CommentDto commentDto2;
    @Mock
    private CommentDto commentDto3;
    @Mock
    private CommentDto commentDtoForSave;

    @Mock
    private CommentService commentService;

    List<CommentDto> commentDtoList;

    @InjectMocks
    private CommentController commentController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        commentDtoList = Stream.of(commentDto1, commentDto2, commentDto3).collect(Collectors.toList());

        setupCommentsDto(commentDto1, 1);
        setupCommentsDto(commentDto2, 2);
        setupCommentsDto(commentDto3, 3);
        setupCommentsDto(commentDtoForSave, null);
    }

    @Test
    public void saveTest() {
        when(commentService.save(commentDtoForSave)).thenReturn(commentDtoForSave);

        ResponseEntity<CommentDto> responseEntity = commentController.save(commentDtoForSave);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(commentDtoForSave, responseEntity.getBody());
    }

    @Test
    public void deleteNoContentTest(){
        int id = 1;
        commentService.deleteById(id);
        ResponseEntity<Void> responseEntity = commentController.deleteById(id);

        assertEquals(204, responseEntity.getStatusCodeValue());
    }


    private void setupCommentsDto(CommentDto commentDto, Integer id) {
        when(commentDto.getId()).thenReturn(id);
    }
}
