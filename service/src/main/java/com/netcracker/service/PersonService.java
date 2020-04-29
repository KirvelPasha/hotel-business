package com.netcracker.service;


import com.netcracker.dto.PersonDto;
import com.netcracker.entity.Person;
import com.netcracker.module.MockPersonLogin;
import com.netcracker.requestbody.PersonUpdate;

public interface PersonService {

    PersonDto getById(Integer id);

    Integer save(PersonDto personDto);

    PersonDto update(PersonUpdate personUpdate);

    Person findByLogin(String login);

    void deleteById(Integer id);

    String login(MockPersonLogin mockPersonLogin);

}
