package com.netcracker;

import com.netcracker.converter.BookingConverter;
import com.netcracker.dto.BookingDto;
import com.netcracker.entity.Booking;
import com.netcracker.repository.BookingRepository;
import com.netcracker.serviceimpl.BookingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        int id = 1;
        when(bookingRepository.findById(id)).thenReturn(Optional.of(booking1));

        assertEquals(bookingDto1, bookingService.getById(id));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByIdTestException() {
        int id = 120;
        when(bookingRepository.findById(id)).thenReturn(Optional.empty());
        bookingService.getById(id);
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
