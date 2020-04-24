package com.netcracker;

import com.netcracker.converter.ApartmentTypeConverter;
import com.netcracker.dto.ApartmentTypeDto;
import com.netcracker.entity.ApartmentTypes;
import com.netcracker.repository.ApartmentTypeRepository;
import com.netcracker.serviceimpl.ApartmentTypeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ApartmentTypeServiceImplTest {

    @Mock
    private ApartmentTypes apartmentTypes1;
    @Mock
    private ApartmentTypes apartmentTypes2;

    @Mock
    private ApartmentTypeDto apartmentTypeDto1;
    @Mock
    private ApartmentTypeDto apartmentTypeDto2;

    @Mock
    private ApartmentTypeConverter apartmentTypeConverter;

    @Mock
    private ApartmentTypeRepository apartmentTypeRepository;

    private List<ApartmentTypes> apartmentTypesList;
    private List<ApartmentTypeDto> apartmentTypeDtoList;

    @InjectMocks
    private ApartmentTypeServiceImpl apartmentTypeService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        apartmentTypesList = Stream.of(apartmentTypes1, apartmentTypes2).collect(Collectors.toList());
        apartmentTypeDtoList = Stream.of(apartmentTypeDto1, apartmentTypeDto2).collect(Collectors.toList());

        setupApartmentType(apartmentTypes1, 1, "vip");
        setupApartmentType(apartmentTypes2, 2, "vip");

        setupApartmentTypeDto(apartmentTypeDto1, 1, "vip");
        setupApartmentTypeDto(apartmentTypeDto2, 2, "vip");

        setupConverterApartmentType(apartmentTypes1, apartmentTypeDto1);
        setupConverterApartmentType(apartmentTypes2, apartmentTypeDto2);
    }

    @Test
    public void getAllTest() {
        when(apartmentTypeRepository.findAll()).thenReturn(apartmentTypesList);
        assertEquals(apartmentTypeDtoList, apartmentTypeService.getAll());
    }

    @Test
    public void getByIdTest() {
        int id = 1;
        when(apartmentTypeRepository.findById(id)).thenReturn(Optional.of(apartmentTypes1));
        assertEquals(apartmentTypeDto1, apartmentTypeService.getById(id));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByIdTestException() {
        int id = 120;
        when(apartmentTypeRepository.findById(id)).thenReturn(Optional.empty());
        apartmentTypeService.getById(id); }

    @Test
    public void saveTest() {
        when(apartmentTypeRepository.save(apartmentTypes1)).thenReturn(apartmentTypes1);
        assertEquals(apartmentTypeDto1, apartmentTypeService.save(apartmentTypeDto1));
    }

    @Test
    public void deleteByIdTest() {
        int id = 1;
        when(apartmentTypeRepository.findById(id)).thenReturn(Optional.of(apartmentTypes1));
        apartmentTypeService.deleteById(id);
        verify(apartmentTypeRepository, times(1)).deleteById(id);
    }


    private void setupApartmentType(ApartmentTypes apartmentTypes, Integer id, String type) {
        when(apartmentTypes.getId()).thenReturn(id);
        when(apartmentTypes.getType()).thenReturn(type);
    }

    private void setupApartmentTypeDto(ApartmentTypeDto apartmentTypeDto, Integer id, String type) {
        when(apartmentTypeDto.getId()).thenReturn(id);
        when(apartmentTypeDto.getType()).thenReturn(type);
    }

    private void setupConverterApartmentType(ApartmentTypes apartmentTypes, ApartmentTypeDto apartmentTypeDto) {
        when(apartmentTypeConverter.converter(apartmentTypes)).thenReturn(apartmentTypeDto);
    }
}
