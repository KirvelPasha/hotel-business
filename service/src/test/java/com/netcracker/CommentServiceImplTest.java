package com.netcracker;

import com.netcracker.converter.CommentConverter;
import com.netcracker.dto.CommentDto;
import com.netcracker.entity.Comments;
import com.netcracker.repository.CommentRepository;
import com.netcracker.serviceimpl.CommentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CommentServiceImplTest {

    @Mock
    private Comments comment1;
    @Mock
    private Comments comment2;
    @Mock
    private Comments comment3;

    @Mock
    private CommentDto commentDto1;
    @Mock
    private CommentDto commentDto2;
    @Mock
    private CommentDto commentDto3;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentConverter commentConverter;

    List<Comments> commentsList;
    List<CommentDto> commentDtoList;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        commentsList = Stream.of(comment1, comment2, comment3).collect(Collectors.toList());
        commentDtoList = Stream.of(commentDto1, commentDto2, commentDto3).collect(Collectors.toList());

        setupComments(comment1, 1);
        setupComments(comment2, 2);
        setupComments(comment3, 3);

        setupCommentsDto(commentDto1, 1);
        setupCommentsDto(commentDto2, 2);
        setupCommentsDto(commentDto3, 3);

        setupConverterComments(comment1, commentDto1);
        setupConverterComments(comment2, commentDto2);
        setupConverterComments(comment3, commentDto3);
    }

    @Test
    public void getByIdTest() {
        int id = 1;
        when(commentRepository.findById(id)).thenReturn(Optional.of(comment1));

        assertEquals(commentDto1, commentService.getById(id));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByIdTestException() {
        int id = 120;
        when(commentRepository.findById(id)).thenReturn(Optional.empty());
        commentService.getById(id);
    }

    private void setupComments(Comments comments, Integer id) {
        when(comments.getId()).thenReturn(id);
    }

    private void setupCommentsDto(CommentDto commentDto, Integer id) {
        when(commentDto.getId()).thenReturn(id);
    }

    private void setupConverterComments(Comments comments, CommentDto commentDto) {
        when(commentConverter.converter(comments)).thenReturn(commentDto);
    }


}
