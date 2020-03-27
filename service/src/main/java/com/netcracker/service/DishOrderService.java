package com.netcracker.service;


import com.netcracker.dto.DishOrderDto;

import java.util.List;

public interface DishOrderService {

    DishOrderDto save(DishOrderDto dishOrderDto);

    void deleteById(Integer id);

    DishOrderDto getById(Integer id);

    List<DishOrderDto> getByPerson_Login();
}
