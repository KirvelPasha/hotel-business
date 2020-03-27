package com.netcracker.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person extends BaseEntity {

    public Person(String login) {
        this.login = login;
    }

    public Person() {
    }

    private String name;

    private String surname;

    private String phoneNumber;

    private String login;

    private String password;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "passportId")
    private Passport passport;

    @OneToMany(mappedBy = "person")
    private Set<DishOrder> dishOrderList;

    @OneToMany(mappedBy = "person")
    private Set<Comments> commentsList;

    @OneToMany(mappedBy = "person")
    private Set<Booking> bookingList;

    @ManyToOne()
    @JoinColumn(name = "roleId")
    private PersonRole personRole;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public void setPersonRole(PersonRole personRole) {
        this.personRole = personRole;
    }

    public Passport getPassport() {
        return passport;
    }

    public PersonRole getPersonRole() {
        return personRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(login, person.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, phoneNumber, login, password);
    }
}
