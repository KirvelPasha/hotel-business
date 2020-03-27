package com.netcracker.converter;

import com.netcracker.dto.DishOrderDto;
import com.netcracker.entity.DishOrder;
import org.springframework.stereotype.Component;

@Component
public class DishOrderConverter {
    public DishOrderDto converter(DishOrder dishOrder) {

        DishOrderDto dishOrderDto = new DishOrderDto();

        dishOrderDto.setId(dishOrder.getId());

        dishOrderDto.setDishId(dishOrder.getDish().getId());

        dishOrderDto.setPersonLogin(dishOrder.getPerson().getLogin());

        return dishOrderDto;
    }
}
