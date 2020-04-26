package com.netcracker;

import com.netcracker.converter.ApartmentConverter;
import com.netcracker.dto.ApartmentDto;
import com.netcracker.entity.Apartment;
import com.netcracker.repository.ApartmentRepository;
import com.netcracker.serviceimpl.ApartmentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ApartmentServiceImplTest {

    @Mock
    private Apartment apartment1;
    @Mock
    private Apartment apartment2;
    @Mock
    private Apartment apartment3;
    @Mock
    private Apartment apartmentForSave;

    @Mock
    private ApartmentDto apartmentDto1;
    @Mock
    private ApartmentDto apartmentDto2;
    @Mock
    private ApartmentDto apartmentDto3;
    @Mock
    private ApartmentDto apartmentDtoForSave;

    @Mock
    private ApartmentConverter apartmentConverter;

    private List<Apartment> apartmentList;
    private List<ApartmentDto> apartmentDtoList;

    private List<ApartmentDto> apartmentListDtoCheaper;
    private List<ApartmentDto> apartmentListDtoByCountRoom;
    private List<ApartmentDto> apartmentListDtoByCountPlaces;
    private List<ApartmentDto> apartmentListDtoByCountPlacesAndRoom;

    @Mock
    private ApartmentRepository apartmentRepository;

    @InjectMocks
    private ApartmentServiceImpl apartmentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        apartmentList = Stream.of(apartment1, apartment2, apartment3).collect(Collectors.toList());
        apartmentDtoList = Stream.of(apartmentDto1, apartmentDto2, apartmentDto3).collect(Collectors.toList());

        setupApartment(apartment1, 1, 140, 2, 5);
        setupApartment(apartment2, 2, 100, 3, 6);
        setupApartment(apartment3, 3, 200, 2, 5);
        setupApartment(apartmentForSave, 4, 200, 2, 5);

        setupApartmentDto(apartmentDto1, 1, 140, 2, 5);
        setupApartmentDto(apartmentDto2, 2, 100, 3, 6);
        setupApartmentDto(apartmentDto3, 3, 200, 2, 5);
        setupApartmentDto(apartmentDtoForSave, null, 200, 2, 5);

        setupConverterApartment(apartment1, apartmentDto1);
        setupConverterApartment(apartment2, apartmentDto2);
        setupConverterApartment(apartment3, apartmentDto3);
    }

    @Test
    public void getAllTest() {
        when(apartmentRepository.findAll()).thenReturn(apartmentList);
        assertEquals(apartmentDtoList, apartmentService.getAll());
    }

    @Test
    public void getByIdTest() {
        int id = 1;
        when(apartmentRepository.findById(id)).thenReturn(Optional.of(apartment1));
        assertEquals(apartmentDto1, apartmentService.getById(id));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByIdTestException() {
        int id = 120;
        when(apartmentRepository.findById(id)).thenReturn(Optional.empty());
        apartmentService.getById(id);
    }

    @Test
    public void getCheaperApartmentsTest() {
        int price = 150;
        List<Apartment> apartmentListCheaper = apartmentList.stream()
                .filter(apartment -> apartment.getPrice() <= price)
                .collect(Collectors.toList());
        apartmentListDtoCheaper = Stream.of(apartmentDto1, apartmentDto2).collect(Collectors.toList());

        when(apartmentRepository.getCheaperApartments(price)).thenReturn(apartmentListCheaper);

        assertEquals(apartmentListDtoCheaper, apartmentService.getCheaperApartments(price));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCheaperApartmentsTestException() {
        apartmentService.getCheaperApartments(-120);
    }

    @Test
    public void getApartmentsByCountRoomsTest() {
        int countRoom = 2;
        List<Apartment> apartmentListByCountRoom = apartmentList.stream()
                .filter(apartment -> apartment.getCountRooms() == countRoom)
                .collect(Collectors.toList());
        apartmentListDtoByCountRoom = Stream.of(apartmentDto1, apartmentDto3).collect(Collectors.toList());

        when(apartmentRepository.getApartmentsByCountRooms(countRoom)).thenReturn(apartmentListByCountRoom);

        assertEquals(apartmentListDtoByCountRoom, apartmentService.getApartmentsByCountRooms(countRoom));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getApartmentsByCountRoomsTestException() {
        apartmentService.getApartmentsByCountRooms(-120);
    }

    @Test
    public void getApartmentsByCountPlacesTest() {
        int countPlaces = 5;
        List<Apartment> apartmentListByCountPlaces = apartmentList.stream()
                .filter(apartment -> apartment.getCountPlaces() == countPlaces)
                .collect(Collectors.toList());
        apartmentListDtoByCountPlaces = Stream.of(apartmentDto1, apartmentDto3).collect(Collectors.toList());

        when(apartmentRepository.getApartmentsByCountPlaces(countPlaces)).thenReturn(apartmentListByCountPlaces);

        assertEquals(apartmentListDtoByCountPlaces, apartmentService.getApartmentsByCountPlaces(countPlaces));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getApartmentsByCountPlacesTestException() {
        apartmentService.getApartmentsByCountPlaces(-5);
    }

    @Test
    public void getApartmentsByCountPlacesAndCountRoomsTest() {
        int countPlaces = 5;
        int countRoom = 2;
        List<Apartment> apartmentListByCountPlacesAndRoom = apartmentList.stream()
                .filter(apartment -> apartment.getCountPlaces() == countPlaces && apartment.getCountRooms() == countRoom)
                .collect(Collectors.toList());
        apartmentListDtoByCountPlacesAndRoom = Stream.of(apartmentDto1, apartmentDto3).collect(Collectors.toList());

        when(apartmentRepository.getApartmentsByCountPlacesAndCountRooms(countPlaces, countRoom))
                .thenReturn(apartmentListByCountPlacesAndRoom);

        assertEquals(apartmentListDtoByCountPlacesAndRoom, apartmentService
                .getApartmentsByCountPlacesAndCountRooms(countPlaces, countRoom));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getApartmentsByCountPlacesAndCountRoomTestException() {
        apartmentService.getApartmentsByCountPlacesAndCountRooms(-5, -6);
    }

    @Test
    public void saveTest() {
        when(apartmentRepository.save(apartmentForSave)).thenReturn(apartmentForSave);
        //To Do
    }

    @Test
    public void deleteTest() {
        int id = 1;
        apartmentService.delete(id);

        verify(apartmentRepository).deleteById(eq(id));
        verify(apartmentRepository, times(1)).deleteById(1);
    }

    private void setupApartment(Apartment apartment, Integer id, int price, int countRoom, int countPlaces) {
        when(apartment.getId()).thenReturn(id);
        when(apartment.getPrice()).thenReturn(price);
        when(apartment.getCountRooms()).thenReturn(countRoom);
        when(apartment.getCountPlaces()).thenReturn(countPlaces);
    }

    private void setupApartmentDto(ApartmentDto apartmentDto, Integer id, int price, int countRoom, int countPlaces) {
        when(apartmentDto.getId()).thenReturn(id);
        when(apartmentDto.getPrice()).thenReturn(price);
        when(apartmentDto.getCountRooms()).thenReturn(countRoom);
        when(apartmentDto.getCountPlaces()).thenReturn(countPlaces);
    }

    private void setupConverterApartment(Apartment apartment, ApartmentDto apartmentDto) {
        when(apartmentConverter.converter(apartment)).thenReturn(apartmentDto);
    }
}
