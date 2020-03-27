package com.netcracker.repository;

import com.netcracker.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface
DishRepository extends JpaRepository<Dish, Integer> {

    @Query("select d from Dish d where price <= :price")
    List<Dish> getCheaperDish(@Param("price") int price);

}
