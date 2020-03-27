package com.netcracker.converter;

import com.netcracker.dto.PersonDto;
import com.netcracker.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {

    private final PassportConverter passportConverter;

    @Autowired
    public PersonConverter(PassportConverter passportConverter) {
        this.passportConverter = passportConverter;
    }

    public Person converter(PersonDto personDto) {
        Person person = new Person();

        person.setId(personDto.getId());

        person.setName(personDto.getName());

        person.setSurname(personDto.getSurname());

        person.setLogin(personDto.getLogin());

        person.setPassword(personDto.getPassword());

        person.setPhoneNumber(personDto.getPhoneNumber());

        person.setPassport(passportConverter.converter(personDto.getPassportDto()));

        return person;
    }

    public PersonDto converter(Person person) {
        PersonDto personDto = new PersonDto();

        personDto.setId(person.getId());

        personDto.setName(person.getName());

        personDto.setSurname(person.getSurname());

        personDto.setLogin(person.getLogin());

        personDto.setPassword(person.getPassword());

        personDto.setPhoneNumber(person.getPhoneNumber());

        personDto.setPassportDto(passportConverter.converter(person.getPassport()));

        return personDto;
    }


}
