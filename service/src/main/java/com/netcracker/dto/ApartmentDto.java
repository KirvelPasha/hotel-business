package com.netcracker.dto;

import javax.validation.constraints.Positive;
import java.util.Objects;

public class ApartmentDto {

    @Positive(message = "Id can't be less than one")
    private Integer id;

    @Positive(message = "Count places can't be less than one")
    private int countPlaces;

    @Positive(message = "Count rooms can't be less than one")
    private int countRooms;

    @Positive(message = "Number can't be less than one")
    private int number;

    @Positive(message = "Price can't be less than one")
    private int price;

    @Positive(message = "Type id can't be less than one")
    private Integer typeId;

    public ApartmentDto(int countPlaces, int countRooms, int number, int price, Integer typeId) {
        this.countPlaces = countPlaces;
        this.countRooms = countRooms;
        this.number = number;
        this.price = price;
        this.typeId = typeId;
    }

    public ApartmentDto() {
    }

    public int getCountPlaces() {
        return countPlaces;
    }

    public void setCountPlaces(int countPlaces) {
        this.countPlaces = countPlaces;
    }

    public int getCountRooms() {
        return countRooms;
    }

    public void setCountRooms(int countRooms) {
        this.countRooms = countRooms;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ApartmentDto{" +
                "id=" + id +
                ", countPlaces=" + countPlaces +
                ", countRooms=" + countRooms +
                ", number=" + number +
                ", price=" + price +
                ", typeId=" + typeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApartmentDto that = (ApartmentDto) o;
        return countPlaces == that.countPlaces &&
                countRooms == that.countRooms &&
                number == that.number &&
                price == that.price &&
                Objects.equals(id, that.id) &&
                Objects.equals(typeId, that.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countPlaces, countRooms, number, price, typeId);
    }
}
