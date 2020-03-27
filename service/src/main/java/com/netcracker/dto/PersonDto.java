package com.netcracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PersonDto {

    private Integer id;

    private PassportDto passportDto;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 30)
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 2, max = 30)
    private String surname;

    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @NotBlank(message = "Login number is mandatory")
    private String login;

    @NotBlank(message = "Password number is mandatory")
    @Size(min = 4, max = 10)
    private String password;

    public PersonDto(PassportDto passportDto, @NotBlank(message = "Name is mandatory") @Size(min = 2, max = 30) String name, @NotBlank(message = "Surname is mandatory") @Size(min = 2, max = 30) String surname, @NotBlank(message = "Phone number is mandatory") String phoneNumber, @NotBlank(message = "Login number is mandatory") String login, @NotBlank(message = "Password number is mandatory") @Size(min = 4, max = 30) String password) {

        this.passportDto = passportDto;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
    }

    public PersonDto() {
    }


    public PassportDto getPassportDto() {
        return passportDto;
    }

    public void setPassportDto(PassportDto passportDto) {
        this.passportDto = passportDto;
    }

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

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
