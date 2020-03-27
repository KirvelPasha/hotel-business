package com.netcracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import java.util.Objects;


public class PassportDto {

    private Integer id;

    @NotBlank(message = "Serila number is mandatory")
    private int serialNumber;

    @NotBlank(message = "Date expire is mandatory")
    private String dateExpire;

    public PassportDto(@NotBlank(message = "Serila number is mandatory") int serialNumber, @NotBlank(message = "Date expire is mandatory") String dateExpire) {
        this.serialNumber = serialNumber;
        this.dateExpire = dateExpire;
    }

    public PassportDto() {
    }

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(String dateExpire) {
        this.dateExpire = dateExpire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassportDto that = (PassportDto) o;
        return serialNumber == that.serialNumber &&
                Objects.equals(id, that.id) &&
                Objects.equals(dateExpire, that.dateExpire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serialNumber, dateExpire);
    }
}
