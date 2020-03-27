package com.netcracker.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "apartment")
public class Apartment extends BaseEntity {

    private int number;

    private int countRooms;

    private int countPlaces;

    private int price;

    @ManyToOne
    @JoinColumn(name = "typeId")
    private ApartmentTypes apartmentTypes;

    @OneToMany(mappedBy = "apartment")
    private Set<Comments> comments;

    @OneToMany(mappedBy = "apartment")
    private Set<Booking> bookingList;

    public Apartment(Integer id, int number, int countRooms, int countPlaces, int price, ApartmentTypes apartmentTypes) {
        super(id);
        this.number = number;
        this.countRooms = countRooms;
        this.countPlaces = countPlaces;
        this.price = price;
        this.apartmentTypes = apartmentTypes;
    }

    public Apartment() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCountRooms() {
        return countRooms;
    }

    public void setCountRooms(int countRooms) {
        this.countRooms = countRooms;
    }

    public int getCountPlaces() {
        return countPlaces;
    }

    public void setCountPlaces(int countPlaces) {
        this.countPlaces = countPlaces;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setApartmentTypes(ApartmentTypes apartmentTypes) {
        this.apartmentTypes = apartmentTypes;
    }

    public ApartmentTypes getApartmentTypes() {
        return apartmentTypes;
    }


    @Override
    public String toString() {
        return "Apartment{" +
                "number=" + number +
                ", countRooms=" + countRooms +
                ", countPlaces=" + countPlaces +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return number == apartment.number &&
                countRooms == apartment.countRooms &&
                countPlaces == apartment.countPlaces &&
                price == apartment.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, countRooms, countPlaces, price);
    }
}

