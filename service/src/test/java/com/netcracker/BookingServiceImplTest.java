package com.netcracker;

import com.netcracker.converter.BookingConverter;
import com.netcracker.dto.ApartmentTypeDto;
import com.netcracker.dto.BookingDto;
import com.netcracker.entity.Apartment;
import com.netcracker.entity.ApartmentTypes;
import com.netcracker.entity.Booking;
import com.netcracker.entity.Person;
import com.netcracker.repository.BookingRepository;
import com.netcracker.serviceimpl.BookingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BookingServiceImplTest {

    @Mock
    Booking booking1;
    @Mock
    Booking booking2;

    @Mock
    BookingDto bookingDto1;
    @Mock
    BookingDto bookingDto2;

    @Mock
    BookingRepository bookingRepository;

    @Mock
    BookingConverter bookingConverter;

    @InjectMocks
    BookingServiceImpl bookingService;

    List<Booking> bookingList;
    List<BookingDto> bookingDtoList;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        setupBooking(booking1, 1);
        setupBooking(booking2, 2);

        setupBookingDto(bookingDto1, 1);
        setupBookingDto(bookingDto2, 2);

        setupConverterBooking(booking1, bookingDto1);
    }

    @Test
    public void getByIdTest() {
        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking1));
        assertEquals(bookingDto1, bookingService.getById(1));
    }

    private void setupBooking(Booking booking, Integer id) {
        when(booking.getId()).thenReturn(id);
    }

    private void setupBookingDto(BookingDto bookingDto, Integer id) {
        when(bookingDto.getId()).thenReturn(id);
    }

    private void setupConverterBooking(Booking booking, BookingDto bookingDto) {
        when(bookingConverter.converter(booking)).thenReturn(bookingDto);
    }
}
