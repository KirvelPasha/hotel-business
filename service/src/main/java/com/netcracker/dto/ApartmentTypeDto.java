package com.netcracker.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class ApartmentTypeDto {

    private Integer id;

    @NotBlank(message = "Type is mandatory")
    private String type;

    public ApartmentTypeDto(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public ApartmentTypeDto() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "ApartmentTypeDto{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApartmentTypeDto that = (ApartmentTypeDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
