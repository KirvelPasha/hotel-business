package com.netcracker.serviceimpl;


import com.netcracker.converter.ApartmentConverter;
import com.netcracker.converter.BookingConverter;
import com.netcracker.converter.PersonConverter;
import com.netcracker.dto.BookingDto;
import com.netcracker.entity.Apartment;
import com.netcracker.entity.Booking;
import com.netcracker.entity.Person;
import com.netcracker.repository.BookingRepository;
import com.netcracker.service.ApartmentService;
import com.netcracker.service.BookingService;
import com.netcracker.service.PersonService;
import com.netcracker.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final PersonService personService;
    private final ApartmentService apartmentService;
    private final BookingConverter bookingConverter;
    private final Validate validate;
    private final ApartmentConverter apartmentConverter;
    private final PersonConverter personConverter;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, PersonService personService,
                              ApartmentService apartmentService, BookingConverter bookingConverter,
                              Validate validate, ApartmentConverter apartmentConverter,
                              PersonConverter personConverter) {

        this.bookingRepository = bookingRepository;
        this.personService = personService;
        this.apartmentService = apartmentService;
        this.bookingConverter = bookingConverter;
        this.validate = validate;
        this.apartmentConverter = apartmentConverter;
        this.personConverter = personConverter;
    }

    @Override
    public BookingDto save(BookingDto bookingDto) {
        if (validate.dates(bookingDto.getStartDate(), bookingDto.getEndDate())) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Person person = personConverter.converter(personService.findByLogin(user.getUsername()));
            Apartment apartment = apartmentConverter.converter(
                    apartmentService.getById(bookingDto.getApartmentId()));
            Booking booking = bookingConverter.converter(bookingDto);
            booking.setPerson(person);
            booking.setApartment(apartment);
            return bookingConverter.converter(bookingRepository.save(booking));
        }
        throw new IllegalArgumentException("Wrong data");
    }

    @Override
    public void deleteById(Integer id) throws IllegalArgumentException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookingDto booking = this.getById(id);
        if (booking.getPersonLogin().equals(user.getUsername())) {
            bookingRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("You can't delete this booking ");
        }
    }

    @Override
    public BookingDto getById(Integer id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            return bookingConverter.converter(optionalBooking.get());
        }
        throw new IllegalArgumentException("No such booking");
    }

    @Override
    public List<BookingDto> getByPerson_Login() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bookingRepository.getByPerson_Login(user.getUsername())
                .stream()
                .map(booking -> bookingConverter.converter(booking))
                .collect(Collectors.toList());
    }
}
