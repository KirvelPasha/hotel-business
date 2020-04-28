package com.netcracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import java.util.Objects;


public class CommentDto {
    private Integer id;
    private Integer apartmentId;

    @NotBlank(message = "Comment is mandatory")
    private String comment;

    private String personLogin;

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDto that = (CommentDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(apartmentId, that.apartmentId) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(personLogin, that.personLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apartmentId, comment, personLogin);
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", apartmentId=" + apartmentId +
                ", comment='" + comment + '\'' +
                ", personLogin='" + personLogin + '\'' +
                '}';
    }
}
