package com.netcracker;

import com.netcracker.controller.BookingController;
import com.netcracker.converter.BookingConverter;
import com.netcracker.dto.BookingDto;
import com.netcracker.serviceimpl.BookingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BookingControllerTest {

    @Mock
    BookingDto bookingDto1;
    @Mock
    BookingDto bookingDto2;
    @Mock
    BookingDto bookingDtoForSave;

    @Mock
    BookingServiceImpl bookingService;

    @Mock
    BookingConverter bookingConverter;

    @InjectMocks
    BookingController bookingController;

    List<BookingDto> bookingDtoList;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        setupBookingDto(bookingDto1, 1);
        setupBookingDto(bookingDto2, 2);
        setupBookingDto(bookingDtoForSave, null);
    }

    @Test
    public void saveTest() {
        when(bookingService.save(bookingDtoForSave)).thenReturn(bookingDtoForSave);

        ResponseEntity<BookingDto> responseEntity = bookingController.save(bookingDtoForSave);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(bookingDtoForSave, responseEntity.getBody());
    }

    @Test
    public void deleteNoContentTest() {
        int id = 1;
        bookingService.deleteById(id);
        ResponseEntity<Void> responseEntity = bookingController.deleteById(id);

        assertEquals(204, responseEntity.getStatusCodeValue());
    }

    private void setupBookingDto(BookingDto bookingDto, Integer id) {
        when(bookingDto.getId()).thenReturn(id);
    }

}
