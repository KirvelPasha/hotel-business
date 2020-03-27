package com.netcracker.serviceimpl;


import com.netcracker.converter.DishConverter;
import com.netcracker.converter.DishOrderConverter;
import com.netcracker.dto.DishOrderDto;
import com.netcracker.entity.Dish;
import com.netcracker.entity.DishOrder;
import com.netcracker.entity.Person;
import com.netcracker.repository.DishOrderRepository;
import com.netcracker.service.DishOrderService;
import com.netcracker.service.DishService;
import com.netcracker.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DishOrderServiceImpl implements DishOrderService {

    private final DishOrderRepository dishOrderRepository;
    private final DishService dishService;
    private final PersonService personService;
    private final DishConverter dishConverter;
    private final DishOrderConverter dishOrderConverter;

    @Autowired
    public DishOrderServiceImpl(DishOrderRepository dishOrderRepository, DishService dishService,
                                PersonService personService, DishConverter dishConverter,
                                DishOrderConverter dishOrderConverter) {
        this.dishOrderRepository = dishOrderRepository;
        this.dishService = dishService;
        this.personService = personService;
        this.dishConverter = dishConverter;
        this.dishOrderConverter = dishOrderConverter;
    }

    public DishOrderDto save(DishOrderDto dishOrderDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DishOrder dishOrder = new DishOrder();
        Dish dish = dishConverter.converter(dishService.getById(dishOrderDto.getDishId()));
        Person person = personService.findByLogin(user.getUsername());
        dishOrder.setDish(dish);
        dishOrder.setPerson(person);
        return dishOrderConverter.converter(dishOrderRepository.save(dishOrder));
    }


    @Override
    public void deleteById(Integer id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DishOrderDto dishOrder = this.getById(id);
        if (user.getUsername().equals(dishOrder.getPersonLogin())) {
            dishOrderRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("You can't delete this dish order ");
        }
    }

    @Override
    public DishOrderDto getById(Integer id) {
        Optional<DishOrder> optionalDishOrder = dishOrderRepository.findById(id);
        if (optionalDishOrder.isPresent()) {
            return dishOrderConverter.converter(optionalDishOrder.get());
        }

        throw new IllegalArgumentException("No such dish order");
    }

    @Override
    public List<DishOrderDto> getByPerson_Login() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return dishOrderRepository.getByPerson_Login(user.getUsername())
                .stream()
                .map(dishOrder -> dishOrderConverter.converter(dishOrder))
                .collect(Collectors.toList());
    }
}
