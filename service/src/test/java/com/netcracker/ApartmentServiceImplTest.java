package com.netcracker;

import com.netcracker.converter.ApartmentConverter;
import com.netcracker.dto.ApartmentDto;
import com.netcracker.entity.Apartment;
import com.netcracker.repository.ApartmentRepository;
import com.netcracker.service.ApartmentService;
import com.netcracker.serviceimpl.ApartmentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ApartmentServiceImplTest {

    @Mock
    private Apartment apartment1;
    @Mock
    private Apartment apartment2;
    @Mock
    private Apartment apartment3;
    @Mock
    private ApartmentDto apartmentDto;
    @Mock
    private ApartmentConverter apartmentConverter;

    private List<Apartment> apartmentList;
    private  List<ApartmentDto> apartmentDtoList;

    @Mock
    private ApartmentRepository apartmentRepository;

    @InjectMocks
    private ApartmentService apartmentService;

    @Before
    public void init() {
        apartmentList = Stream.of(apartment).collect(Collectors.toList());
        apartmentDtoList = Stream.of(apartmentDto).collect(Collectors.toList());

        setupApartment(apartment1, 1);
        setupApartment(apartment2, 2);
        setupApartment(apartment3, 3);
    }

    @Test
    public void getAllTest() {
        when(apartmentRepository.findAll()).thenReturn(apartmentList);
        when(apartmentConverter.converter(apartment)).thenReturn(apartmentDto);
        assertEquals(apartmentDtoList, apartmentService.getAll());

    }

    private void setupApartment(Apartment apartment, Integer id) {
        when(apartment.getId()).thenReturn(id);
    }
}
