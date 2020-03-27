package com.netcracker.service;


import com.netcracker.dto.BookingDto;

import java.util.List;

public interface BookingService {

    BookingDto save(BookingDto bookingDto);

    void deleteById(Integer id);

    BookingDto getById(Integer id);

    List<BookingDto> getByPerson_Login();
}
