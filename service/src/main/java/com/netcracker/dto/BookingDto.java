package com.netcracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;


public class BookingDto {

    private Integer id;

    @NotNull
    private Integer apartmentId;

    @NotBlank(message = "startDate is mandatory")
    private String startDate;

    @NotBlank(message = "endDate is mandatory")
    private String endDate;


    private String personLogin;


    public BookingDto(String startDate, String endDate, Integer apartmentId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.apartmentId = apartmentId;
    }

    public BookingDto() {

    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public String getPersonLogin() {
        return personLogin;
    }

    public void setPersonLogin(String personLogin) {
        this.personLogin = personLogin;
    }

    @Override
    public String toString() {
        return "BookingDto{" +
                "id=" + id +
                ", apartmentId=" + apartmentId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDto that = (BookingDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(apartmentId, that.apartmentId) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(personLogin, that.personLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apartmentId, startDate, endDate, personLogin);
    }
}
