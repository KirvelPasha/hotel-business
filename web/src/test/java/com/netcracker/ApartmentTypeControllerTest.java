package com.netcracker;

import com.netcracker.controller.ApartmentTypeController;
import com.netcracker.dto.ApartmentTypeDto;
import com.netcracker.serviceimpl.ApartmentTypeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ApartmentTypeControllerTest {

    @Mock
    private ApartmentTypeDto apartmentTypeDto1;
    @Mock
    private ApartmentTypeDto apartmentTypeDto2;
    @Mock
    private ApartmentTypeDto apartmentTypeDtoForSave;

    @Mock
    private ApartmentTypeServiceImpl apartmentTypeService;

    private List<ApartmentTypeDto> apartmentTypeDtoList;

    @InjectMocks
    private ApartmentTypeController apartmentTypeController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        apartmentTypeDtoList = Stream.of(apartmentTypeDto1, apartmentTypeDto2).collect(Collectors.toList());


        setupApartmentTypeDto(apartmentTypeDto1, 1, "vip");
        setupApartmentTypeDto(apartmentTypeDto2, 2, "vip");
        setupApartmentTypeDto(apartmentTypeDtoForSave, null, "simple");
    }

    @Test
    public void getAllTest() {
        when(apartmentTypeService.getAll()).thenReturn(apartmentTypeDtoList);
        ResponseEntity<List<ApartmentTypeDto>> responseEntity = apartmentTypeController.getAll();

        assertEquals(responseEntity.getBody().size(), apartmentTypeDtoList.size());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void getByIdTest() {
        int id = 1;
        when(apartmentTypeService.getById(id)).thenReturn(apartmentTypeDto1);
        ResponseEntity<ApartmentTypeDto> responseEntity = apartmentTypeController.getById(id);

        assertEquals(apartmentTypeDto1, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());

    }

    @Test
    public void saveTest() {
        when(apartmentTypeService.save(apartmentTypeDtoForSave)).thenReturn(apartmentTypeDtoForSave);

        ResponseEntity<ApartmentTypeDto> responseEntity = apartmentTypeController.save(apartmentTypeDtoForSave);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(apartmentTypeDtoForSave, responseEntity.getBody());
    }

    private void setupApartmentTypeDto(ApartmentTypeDto apartmentTypeDto, Integer id, String type) {
        when(apartmentTypeDto.getId()).thenReturn(id);
        when(apartmentTypeDto.getType()).thenReturn(type);
    }
}
