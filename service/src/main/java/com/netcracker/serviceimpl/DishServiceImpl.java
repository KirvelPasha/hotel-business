package com.netcracker.serviceimpl;

import com.netcracker.converter.DishConverter;
import com.netcracker.dto.DishDto;
import com.netcracker.entity.Dish;
import com.netcracker.repository.DishRepository;
import com.netcracker.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final DishConverter dishConverter;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository, DishConverter dishConverter) {
        this.dishRepository = dishRepository;
        this.dishConverter = dishConverter;
    }


    @Override
    public DishDto getById(Integer id) {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isPresent()) {
            return dishConverter.converter(optionalDish.get());
        }
        throw new IllegalArgumentException("No such dish");
    }

    @Override
    public List<DishDto> getCheaperDish(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price is less than 0");
        }
        return dishRepository.getCheaperDish(price)
                .stream()
                .map(dish -> dishConverter.converter(dish))
                .collect(Collectors.toList());
    }

    @Override
    public List<DishDto> getAll() {
        return dishRepository.findAll()
                .stream()
                .map(dish -> dishConverter.converter(dish))
                .collect(Collectors.toList());
    }
}
