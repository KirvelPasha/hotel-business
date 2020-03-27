package com.netcracker.dto;

import java.util.Objects;

public class DishDto {

    private Integer id;

    private String name;

    private int price;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishDto dishDto = (DishDto) o;
        return price == dishDto.price &&
                Objects.equals(id, dishDto.id) &&
                Objects.equals(name, dishDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
