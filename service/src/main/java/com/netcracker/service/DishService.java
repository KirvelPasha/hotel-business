package com.netcracker.service;


import com.netcracker.dto.DishDto;

import java.util.List;

public interface DishService {

    DishDto getById(Integer id);

    List<DishDto> getCheaperDish(int price);

    List<DishDto> getAll();
}
