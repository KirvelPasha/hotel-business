package com.netcracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import java.util.Objects;


public class CommentDto {

    private Integer id;

    private Integer ApartmentId;

    @NotBlank(message = "Comment is mandatory")
    private String comment;

    private String personLogin;

    public Integer getApartmentId() {
        return ApartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        ApartmentId = apartmentId;
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
                Objects.equals(ApartmentId, that.ApartmentId) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(personLogin, that.personLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ApartmentId, comment, personLogin);
    }
}
