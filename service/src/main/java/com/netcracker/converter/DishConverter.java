package com.netcracker.converter;

import com.netcracker.dto.DishDto;
import com.netcracker.entity.Dish;
import org.springframework.stereotype.Component;

@Component
public class DishConverter {


    public DishDto converter(Dish dish) {

        DishDto dishDto = new DishDto();

        dishDto.setId(dish.getId());

        dishDto.setName(dish.getName());

        dishDto.setPrice(dish.getPrice());

        return dishDto;
    }

    public Dish converter(DishDto dishDto) {
        Dish dish = new Dish();

        dish.setId(dishDto.getId());

        dish.setName(dishDto.getName());

        dish.setPrice(dishDto.getPrice());

        return dish;

    }
}
