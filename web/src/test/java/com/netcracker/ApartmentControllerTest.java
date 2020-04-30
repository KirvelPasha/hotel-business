package com.netcracker;

import com.netcracker.controller.ApartmentController;
import com.netcracker.converter.ApartmentConverter;
import com.netcracker.dto.ApartmentDto;
import com.netcracker.serviceimpl.ApartmentServiceImpl;
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

public class ApartmentControllerTest {

    @Mock
    private ApartmentDto apartmentDto1;
    @Mock
    private ApartmentDto apartmentDto2;
    @Mock
    private ApartmentDto apartmentDto3;
    @Mock
    private ApartmentDto apartmentDtoForSave;

    @Mock
    ApartmentServiceImpl apartmentService;

    @Mock
    ApartmentConverter apartmentConverter;

    private List<ApartmentDto> apartmentDtoList;

    @InjectMocks
    ApartmentController apartmentController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        apartmentDtoList = Stream.of(apartmentDto1, apartmentDto2, apartmentDto3).collect(Collectors.toList());

        setupApartmentDto(apartmentDto1, 1, 140, 2, 5);
        setupApartmentDto(apartmentDto2, 2, 100, 3, 6);
        setupApartmentDto(apartmentDto3, 3, 200, 2, 5);
        setupApartmentDto(apartmentDtoForSave, null, 200, 2, 5);
    }

    @Test
    public void getAllTest() {
        when(apartmentService.getAll()).thenReturn(apartmentDtoList);
        ResponseEntity<List<ApartmentDto>> responseEntity = apartmentController.getAll();

        assertEquals(responseEntity.getBody().size(), apartmentDtoList.size());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void getByIdTest() {
        int id = 1;
        when(apartmentService.getById(id)).thenReturn(apartmentDto1);

        ResponseEntity<ApartmentDto> responseEntity = apartmentController.getById(id);

        assertEquals(apartmentDto1, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void getCheaperApartmentsTest() {
        int price = 150;
        List<ApartmentDto> apartmentListDtoCheaper = apartmentDtoList.stream()
                .filter(apartmentDto -> apartmentDto.getPrice() <= price)
                .collect(Collectors.toList());

        when(apartmentService.getCheaperApartments(price)).thenReturn(apartmentListDtoCheaper);
        ResponseEntity<List<ApartmentDto>> responseEntity = apartmentController.getCheaperApartments(price);

        assertEquals(apartmentListDtoCheaper, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void getApartmentsByCountRoomsTest() {
        int countRoom = 2;
        List<ApartmentDto> apartmentListDtoByCountRoom = apartmentDtoList.stream()
                .filter(apartmentDto -> apartmentDto.getCountRooms() == countRoom)
                .collect(Collectors.toList());

        when(apartmentService.getApartmentsByCountRooms(countRoom)).thenReturn(apartmentListDtoByCountRoom);
        ResponseEntity<List<ApartmentDto>> responseEntity = apartmentController.getByCountRooms(countRoom);

        assertEquals(apartmentListDtoByCountRoom, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void getApartmentsByCountPlacesTest() {
        int countPlaces = 5;
        List<ApartmentDto> apartmentListDtoByCountPlaces = apartmentDtoList.stream()
                .filter(apartmentDto -> apartmentDto.getCountPlaces() == countPlaces)
                .collect(Collectors.toList());

        when(apartmentService.getApartmentsByCountPlaces(countPlaces)).thenReturn(apartmentListDtoByCountPlaces);
        ResponseEntity<List<ApartmentDto>> responseEntity = apartmentController.getApartmentsByCountPlaces(countPlaces);

        assertEquals(apartmentListDtoByCountPlaces, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void getApartmentsByCountPlacesAndCountRoomsTest() {
        int countPlaces = 5;
        int countRoom = 2;
        List<ApartmentDto> apartmentListDtoByCountPlacesAndRoom = apartmentDtoList.stream()
                .filter(apartmentDto -> apartmentDto.getCountPlaces() == countPlaces && apartmentDto.getCountRooms() == countRoom)
                .collect(Collectors.toList());

        when(apartmentService.getApartmentsByCountPlacesAndCountRooms(countPlaces, countRoom))
                .thenReturn(apartmentListDtoByCountPlacesAndRoom);
        ResponseEntity<List<ApartmentDto>> responseEntity = apartmentController
                .getApartmentsByCountPlacesAndCountRooms(countPlaces, countRoom);

        assertEquals(apartmentListDtoByCountPlacesAndRoom, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void saveTest() {
        when(apartmentService.save(apartmentDtoForSave)).thenReturn(apartmentDtoForSave);

        ResponseEntity<ApartmentDto> responseEntity = apartmentController.save(apartmentDtoForSave);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(apartmentDtoForSave, responseEntity.getBody());
    }


    private void setupApartmentDto(ApartmentDto apartmentDto, Integer id, int price, int countRoom, int countPlaces) {
        when(apartmentDto.getId()).thenReturn(id);
        when(apartmentDto.getPrice()).thenReturn(price);
        when(apartmentDto.getCountRooms()).thenReturn(countRoom);
        when(apartmentDto.getCountPlaces()).thenReturn(countPlaces);
    }
}
