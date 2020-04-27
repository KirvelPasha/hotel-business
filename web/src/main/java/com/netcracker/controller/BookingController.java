package com.netcracker.controller;

import com.netcracker.dto.BookingDto;
import com.netcracker.service.BookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @ApiOperation(value = "Creates booking")
    @PostMapping()
    ResponseEntity<BookingDto> save(@Validated @RequestBody BookingDto bookingDto) {
        log.info("save , BookingController");
        return new ResponseEntity<>(bookingService.save(bookingDto)
                , HttpStatus.CREATED);
    }


    @ApiOperation(value = "Gets all bookings by person's login")
    @GetMapping()
    ResponseEntity<List<BookingDto>> getByPersonLogin() {
        log.info("getByPersonLogin , BookingController");
        return new ResponseEntity<>(bookingService.getByPerson_Login(),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes booking")
    @DeleteMapping()
    ResponseEntity<Void> deleteById(@RequestParam("id") Integer id) {
        log.info("deleteById , BookingController");
        bookingService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
