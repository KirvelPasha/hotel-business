package com.netcracker.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comments extends BaseEntity {

    private String comment;

    @ManyToOne
    @JoinColumn(name = "personId")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "apartmentId")
    private Apartment apartment;


    public Comments(Integer id, String comment, Person person, Apartment apartment) {
        super(id);
        this.comment = comment;
        this.person = person;
        this.apartment = apartment;
    }

    public Comments() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Comments comments = (Comments) o;
        return Objects.equals(comment, comments.comment) &&
                Objects.equals(person, comments.person) &&
                Objects.equals(apartment, comments.apartment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), comment, person, apartment);
    }
}
