package com.netcracker.repository;

import com.netcracker.entity.DishOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishOrderRepository extends JpaRepository<DishOrder, Integer> {

    List<DishOrder> getByPerson_Login(String login);
}
