package com.netcracker.converter;


import com.netcracker.dto.BookingDto;
import com.netcracker.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingConverter {

    public Booking converter(BookingDto bookingDto) {
        Booking booking = new Booking();

        booking.setStartDate(bookingDto.getStartDate());

        booking.setEndDate(bookingDto.getEndDate());

        return booking;
    }

    public BookingDto converter(Booking booking) {
        BookingDto bookingDto = new BookingDto();

        bookingDto.setId(booking.getId());

        bookingDto.setStartDate(booking.getStartDate());

        bookingDto.setEndDate(booking.getEndDate());

        bookingDto.setApartmentId(booking.getApartment().getId());

        bookingDto.setPersonLogin(booking.getPerson().getLogin());

        return bookingDto;
    }

}
