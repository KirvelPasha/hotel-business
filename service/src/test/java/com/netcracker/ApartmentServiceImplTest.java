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
import org.mockito.MockitoAnnotations;
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
    private Apartment apartment;
    @Mock
    private ApartmentDto apartmentDto;
    @Mock
    private ApartmentConverter apartmentConverter;

    private List<Apartment> apartmentList;
    private  List<ApartmentDto> apartmentDtoList;

    @Mock
    private ApartmentRepository apartmentRepository;

    @InjectMocks
    private ApartmentServiceImpl apartmentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        apartmentList = Stream.of(apartment).collect(Collectors.toList());
        apartmentDtoList = Stream.of(apartmentDto).collect(Collectors.toList());
    }

    @Test
    public void getAllTest() {
        when(apartmentRepository.findAll()).thenReturn(apartmentList);
        when(apartmentConverter.converter(apartment)).thenReturn(apartmentDto);
        assertEquals(apartmentDtoList, apartmentService.getAll());
    }
}
