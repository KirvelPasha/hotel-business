package com.netcracker;

import com.netcracker.controller.DishOrderController;
import com.netcracker.converter.DishOrderConverter;
import com.netcracker.dto.BookingDto;
import com.netcracker.dto.DishOrderDto;
import com.netcracker.entity.DishOrder;
import com.netcracker.repository.DishOrderRepository;
import com.netcracker.serviceimpl.DishOrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class DishOrderControllerTest {

    @Mock
    private DishOrderDto dishOrderDto1;
    @Mock
    private DishOrderDto dishOrderDto2;
    @Mock
    DishOrderDto dishOrderDtoForSave;

    @Mock
    private DishOrderServiceImpl dishOrderService;

    @Mock
    private DishOrderConverter dishOrderConverter;

    private List<DishOrder> dishOrderList;
    private List<DishOrderDto> dishOrderDtoList;

    @InjectMocks
    private DishOrderController dishOrderController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        dishOrderDtoList = Stream.of(dishOrderDto1, dishOrderDto2).collect(Collectors.toList());

        setupDishOrderDto(dishOrderDto1, 1);
        setupDishOrderDto(dishOrderDto2, 2);
        setupDishOrderDto(dishOrderDtoForSave, null);
    }

    @Test
    public void saveTest() {
        when(dishOrderService.save(dishOrderDtoForSave)).thenReturn(dishOrderDtoForSave);

        ResponseEntity<DishOrderDto> responseEntity = dishOrderController.save(dishOrderDtoForSave);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(dishOrderDtoForSave, responseEntity.getBody());
    }

    @Test
    public void deleteNoContentTest(){
        int id = 1;
        dishOrderService.deleteById(id);
        ResponseEntity<Void> responseEntity = dishOrderController.deleteById(id);

        assertEquals(204, responseEntity.getStatusCodeValue());
    }

    private void setupDishOrderDto(DishOrderDto dishOrderDto, Integer id) {
        when(dishOrderDto.getId()).thenReturn(id);
    }

    private void setupConverterDishOrder(DishOrder dishOrder, DishOrderDto dishOrderDto) {
        when(dishOrderConverter.converter(dishOrder)).thenReturn(dishOrderDto);
    }
}
