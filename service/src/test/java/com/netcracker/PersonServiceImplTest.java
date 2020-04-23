package com.netcracker;

import com.netcracker.converter.PersonConverter;
import com.netcracker.dto.ApartmentTypeDto;
import com.netcracker.dto.PersonDto;
import com.netcracker.entity.ApartmentTypes;
import com.netcracker.entity.Person;
import com.netcracker.exceptions.PersonNotFoundException;
import com.netcracker.repository.PersonRepository;
import com.netcracker.serviceimpl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PersonServiceImplTest {

    private final String LOGIN = "qwerty";

    @Mock
    Person person;

    @Mock
    PersonDto personDto;

    @Mock
    PersonRepository personRepository;

    @Mock
    PersonConverter personConverter;

    @InjectMocks
    PersonServiceImpl personService;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        setupPerson(person, 1, LOGIN);
        setupPersonDto(personDto,1, LOGIN);
      }

      @Test
      public void getById() {
        when(personRepository.findById(1)).thenReturn(Optional.of(person));
        setupConverterPerson(person, personDto);
        assertEquals(personDto, personService.getById(1));
      }

    @Test(expected = PersonNotFoundException.class)
    public void findByIdTestException(){ personService.getById(1);}

    @Test
      public void getByLogin() {
        when(personRepository.findByLogin(LOGIN)).thenReturn(Optional.of(person));
          assertEquals(person, personService.findByLogin(LOGIN));
    }

    @Test(expected = PersonNotFoundException.class)
    public void findByLoginTestException(){ personService.findByLogin(LOGIN);}

    private void setupPerson(Person person, Integer id, String login) {
        when(person.getId()).thenReturn(id);
        when(person.getLogin()).thenReturn(login);
    }

    private void setupPersonDto(PersonDto personDto, Integer id, String login) {
        when(personDto.getId()).thenReturn(id);
        when(personDto.getLogin()).thenReturn(login);
    }

    private void setupConverterPerson(Person person, PersonDto personDto) {
        when(personConverter.converter(person)).thenReturn(personDto);
    }

}
