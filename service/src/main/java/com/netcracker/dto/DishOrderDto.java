package com.netcracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class DishOrderDto {

    private Integer id;

    private Integer dishId;

    private String personLogin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getPersonLogin() {
        return personLogin;
    }

    @JsonIgnore
    public void setPersonLogin(String personLogin) {
        this.personLogin = personLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishOrderDto that = (DishOrderDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dishId, that.dishId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dishId);
    }
}
