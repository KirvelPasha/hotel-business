package com.netcracker;

import com.netcracker.converter.PassportConverter;
import com.netcracker.dto.ApartmentTypeDto;
import com.netcracker.dto.PassportDto;
import com.netcracker.entity.ApartmentTypes;
import com.netcracker.entity.Passport;
import com.netcracker.repository.PassportRepository;
import com.netcracker.serviceimpl.PassportServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PassportServiceImplTest {

    @Mock
    Passport passport1;
    @Mock
    Passport passport2;

    @Mock
    PassportDto passportDto1;
    @Mock
    PassportDto passportDto2;

    @Mock
    PassportRepository passportRepository;

    @Mock
    PassportConverter passportConverter;

    @InjectMocks
    PassportServiceImpl passportService;

    List<Passport> passportList;
    List<PassportDto> passportDtoList;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        passportList = Stream.of(passport1, passport2).collect(Collectors.toList());
        passportDtoList = Stream.of(passportDto1, passportDto2).collect(Collectors.toList());

        setupPassport(passport1, 1, 1234567, "16/12/2020");
        setupPassport(passport2, 1, 1434567, "16/11/2020");
    }


    private void setupPassport(Passport passport, Integer id, int serialNumber, String dateExpire) {
        when(passport.getId()).thenReturn(id);
        when(passport.getSerialNumber()).thenReturn(serialNumber);
        when(passport.getDateExpire()).thenReturn(dateExpire);

    }

    private void setupPassportDto(PassportDto passportDto, Integer id, int serialNumber, String dateExpire) {
        when(passportDto.getId()).thenReturn(id);
        when(passportDto.getSerialNumber()).thenReturn(serialNumber);
        when(passportDto.getDateExpire()).thenReturn(dateExpire);
    }

    private void setupConverterPassport(Passport passport, PassportDto passportDto) {
        when(passportConverter.converter(passport)).thenReturn(passportDto);
    }
}
