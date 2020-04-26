package com.netcracker;

import com.netcracker.controller.DishController;
import com.netcracker.dto.DishDto;
import com.netcracker.serviceimpl.DishServiceImpl;
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

public class DishControllerTest {

    @Mock
    private DishDto dishDto1;
    @Mock
    private DishDto dishDto2;
    @Mock
    private DishDto dishDto3;

    @Mock
    private DishServiceImpl dishService;

    private List<DishDto> dishDtoList;

    @InjectMocks
    private DishController dishController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        dishDtoList = Stream.of(dishDto1, dishDto2, dishDto3).collect(Collectors.toList());

        setupDishDto(dishDto1, 1, 10);
        setupDishDto(dishDto2, 2, 12);
        setupDishDto(dishDto3, 3, 9);
    }

    @Test
    public void getCheaperDishTest() {
        int price = 11;
        List<DishDto> dishDtoCheaperList = dishDtoList.stream()
                .filter(apartmentDto -> apartmentDto.getPrice() <= price)
                .collect(Collectors.toList());
        when(dishService.getCheaperDish(price)).thenReturn(dishDtoCheaperList);

        ResponseEntity<List<DishDto>> responseEntity = dishController.getCheaperDish(price);

        assertEquals(dishDtoCheaperList, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void getAllTest() {
        when(dishService.getAll()).thenReturn(dishDtoList);

        ResponseEntity<List<DishDto>> responseEntity = dishController.getAll();

        assertEquals(dishDtoList, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    private void setupDishDto(DishDto dishDto, Integer id, int price) {
        when(dishDto.getId()).thenReturn(id);
        when(dishDto.getPrice()).thenReturn(price);
    }

}
