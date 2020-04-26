package com.netcracker;

import com.netcracker.controller.PersonController;
import com.netcracker.converter.PersonConverter;
import com.netcracker.dto.PersonDto;
import com.netcracker.serviceimpl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PersonControllerTest {

    private final String LOGIN = "qwerty";
    private final String LOGIN_FOR_SAVE = "asdfg";

    @Mock
    PersonDto personDto;
    @Mock
    PersonDto personDtoForSave;

    @Mock
    PersonServiceImpl personService;

    @Mock
    PersonConverter personConverter;

    @InjectMocks
    PersonController personController;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        setupPersonDto(personDto, 1, LOGIN);
        setupPersonDto(personDto, null, LOGIN_FOR_SAVE);
    }

    @Test
    public void getById() {
        int id = 1;
        when(personService.getById(id)).thenReturn(personDto);
        ResponseEntity<PersonDto> responseEntity = personController.getById(id);

        assertEquals(responseEntity.getBody(), personDto);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

   /* @Test
    public void getByLogin() {
        when(personService.findByLogin(LOGIN)).thenReturn(personDto);
        ResponseEntity<PersonDto> responseEntity = personController.getByLogin(LOGIN);

        assertEquals(responseEntity.getBody(), personDto);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }*/

    @Test
    public void deleteNoContentTest() {
        int id = 1;
        personService.deleteById(id);
        ResponseEntity<Void> responseEntity = personController.deleteById(id);

        assertEquals(204, responseEntity.getStatusCodeValue());
    }

    private void setupPersonDto(PersonDto personDto, Integer id, String login) {
        when(personDto.getId()).thenReturn(id);
        when(personDto.getLogin()).thenReturn(login);
    }
}
