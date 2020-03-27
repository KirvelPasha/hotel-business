package com.netcracker.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "booking")
public class Booking extends BaseEntity {


    public Booking(Integer id, String startDate, String endDate, Person person, Apartment apartment) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
        this.person = person;
        this.apartment = apartment;
    }

    public Booking() {
    }

    private String startDate;


    private String endDate;

    @ManyToOne
    @JoinColumn(name = "personId")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "apartmentId")
    private Apartment apartment;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", person=" + person +
                ", apartment=" + apartment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Booking booking = (Booking) o;
        return Objects.equals(startDate, booking.startDate) &&
                Objects.equals(endDate, booking.endDate) &&
                Objects.equals(person, booking.person) &&
                Objects.equals(apartment, booking.apartment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startDate, endDate, person, apartment);
    }
}
