package com.netcracker;

import com.netcracker.converter.DishOrderConverter;
import com.netcracker.dto.DishOrderDto;
import com.netcracker.entity.DishOrder;
import com.netcracker.repository.DishOrderRepository;
import com.netcracker.serviceimpl.DishOrderServiceImpl;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class DishOrderServiceImplTest {

    @Mock
    private DishOrder dishOrder1;
    @Mock
    private DishOrder dishOrder2;

    @Mock
    private DishOrderDto dishOrderDto1;
    @Mock
    private DishOrderDto dishOrderDto2;

    @Mock
    private DishOrderRepository dishOrderRepository;

    @Mock
    private DishOrderConverter dishOrderConverter;

    private List<DishOrder> dishOrderList;
    private List<DishOrderDto> dishOrderDtoList;

    @InjectMocks
    private DishOrderServiceImpl dishOrderService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        dishOrderList = Stream.of(dishOrder1, dishOrder2).collect(Collectors.toList());
        dishOrderDtoList = Stream.of(dishOrderDto1, dishOrderDto2).collect(Collectors.toList());

        setupDishOrder(dishOrder1, 1);
        setupDishOrder(dishOrder2, 2);

        setupDishOrderDto(dishOrderDto1, 1);
        setupDishOrderDto(dishOrderDto2, 2);

        setupConverterDishOrder(dishOrder1, dishOrderDto1);
        setupConverterDishOrder(dishOrder2, dishOrderDto2);
    }

    @Test
    public void getByIdTest() {
        int id = 1;
        when(dishOrderRepository.findById(id)).thenReturn(Optional.of(dishOrder1));

        assertEquals(dishOrderDto1, dishOrderService.getById(id));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByIdTestException() {
        int id = 120;
        when(dishOrderRepository.findById(id)).thenReturn(Optional.empty());
        dishOrderService.getById(id);
    }

    private void setupDishOrder(DishOrder dishOrder, Integer id) {
        when(dishOrder.getId()).thenReturn(id);
    }

    private void setupDishOrderDto(DishOrderDto dishOrderDto, Integer id) {
        when(dishOrderDto.getId()).thenReturn(id);
    }

    private void setupConverterDishOrder(DishOrder dishOrder, DishOrderDto dishOrderDto) {
        when(dishOrderConverter.converter(dishOrder)).thenReturn(dishOrderDto);
    }
}
