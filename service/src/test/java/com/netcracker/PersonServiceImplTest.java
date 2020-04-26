package com.netcracker;

import com.netcracker.converter.PersonConverter;
import com.netcracker.dto.PersonDto;
import com.netcracker.entity.Person;
import com.netcracker.exceptions.PersonNotFoundException;
import com.netcracker.repository.PersonRepository;
import com.netcracker.serviceimpl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

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
        setupPersonDto(personDto, 1, LOGIN);
    }

    @Test
    public void getById() {
        int id = 1;
        when(personRepository.findById(id)).thenReturn(Optional.of(person));
        setupConverterPerson(person, personDto);

        assertEquals(personDto, personService.getById(id));
    }

    @Test(expected = PersonNotFoundException.class)
    public void getByIdTestException() {
        int id = 120;
        when(personRepository.findById(id)).thenReturn(Optional.empty());
        personService.getById(id);
    }

    @Test
    public void getByLogin() {
        when(personRepository.findByLogin(LOGIN)).thenReturn(Optional.of(person));
        assertEquals(person, personService.findByLogin(LOGIN));
    }

    @Test(expected = PersonNotFoundException.class)
    public void getByLoginTestException() {
        String login = "one";
        when(personRepository.findByLogin(login)).thenReturn(Optional.empty());
        personService.findByLogin(login);
    }

    @Test
    public void deleteById(){
        int id = 1;
        when(personRepository.findById(id)).thenReturn(Optional.of(person));
        personService.deleteById(id);

        verify(personRepository).deleteById(eq(id));
        verify(personRepository, times(1)).deleteById(id);
    }

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
