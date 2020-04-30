package com.netcracker.service;


import com.netcracker.dto.CommentDto;

import java.util.List;

public interface CommentService {

    void save(CommentDto commentDto);

    List<CommentDto> getAllByApartment_Id(Integer apartmentId);

    void deleteById(Integer id);

    CommentDto getById(Integer id);

    List<CommentDto> getByPerson_Login();
}
