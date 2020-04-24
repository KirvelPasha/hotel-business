package com.netcracker;

import com.netcracker.converter.DishConverter;
import com.netcracker.dto.DishDto;
import com.netcracker.entity.Apartment;
import com.netcracker.entity.Dish;
import com.netcracker.repository.DishRepository;
import com.netcracker.serviceimpl.DishServiceImpl;
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
import static org.mockito.Mockito.when;

public class DishServiceImplTest {

    @Mock
    private Dish dish1;
    @Mock
    private Dish dish2;
    @Mock
    private Dish dish3;

    @Mock
    private DishDto dishDto1;
    @Mock
    private DishDto dishDto2;
    @Mock
    private DishDto dishDto3;

    @Mock
    private DishRepository dishRepository;

    @Mock
    private DishConverter dishConverter;

    private List<Dish> dishList;
    private List<DishDto> dishDtoList;
    private List<DishDto> dishDtoCheaperList;

    @InjectMocks
    private DishServiceImpl dishService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        dishList = Stream.of(dish1, dish2, dish3).collect(Collectors.toList());
        dishDtoList = Stream.of(dishDto1, dishDto2, dishDto3).collect(Collectors.toList());

        setupDish(dish1, 1, 10);
        setupDish(dish2,2,12);
        setupDish(dish3, 3,9);

        setupDishDto(dishDto1, 1, 10);
        setupDishDto(dishDto2,2,12);
        setupDishDto(dishDto3,3,9);

        setupConverterDish(dish1, dishDto1);
        setupConverterDish(dish2, dishDto2);
        setupConverterDish(dish3, dishDto3);
    }

    @Test
    public void getAllTest() {
        when(dishRepository.findAll()).thenReturn(dishList);
        assertEquals(dishDtoList, dishService.getAll());
    }

    @Test
    public void getByIdTest() {
        when(dishRepository.findById(1)).thenReturn(Optional.of(dish1));
        assertEquals(dishDto1, dishService.getById(1));
    }

    @Test
    public void getCheaperDishTest() {
        int price = 11;
        List<Dish> dishCheaperList = dishList.stream()
                .filter(dish -> dish.getPrice() <= price)
                .collect(Collectors.toList());
        when(dishRepository.getCheaperDish(price)).thenReturn(dishCheaperList);
        assertEquals(dishDtoCheaperList, dishService.getCheaperDish(11));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCheaperDishTestException() {
        dishService.getCheaperDish(-120);
    }

    private void setupDish(Dish dish, Integer id, int price) {
        when(dish.getId()).thenReturn(id);
        when(dish.getPrice()).thenReturn(price);
    }

    private void setupDishDto(DishDto dishDto, Integer id, int price) {
        when(dishDto.getId()).thenReturn(id);
        when(dishDto.getPrice()).thenReturn(price);
    }

    private void setupConverterDish(Dish dish, DishDto dishDto) {
        when(dishConverter.converter(dish)).thenReturn(dishDto);
    }
}
