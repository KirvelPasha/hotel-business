package com.netcracker;

import com.netcracker.controller.PersonRoleController;
import com.netcracker.converter.PersonRoleConverter;
import com.netcracker.dto.ApartmentDto;
import com.netcracker.dto.PersonRoleDto;
import com.netcracker.entity.PersonRole;
import com.netcracker.repository.PersonRoleRepository;
import com.netcracker.serviceimpl.PersonRoleServiceImpl;
import com.netcracker.serviceimpl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PersonRoleControllerTest {

    private final String ROLE_FIRST_PERSON = "role1";
    private final String ROLE_SECOND_PERSON = "role2";

    @Mock
    PersonRoleDto personRoleDto1;
    @Mock
    PersonRoleDto personRoleDto2;

    @Mock
    PersonRoleServiceImpl personRoleService;

    List<PersonRoleDto> personRoleDtoList;

    @InjectMocks
    PersonRoleController personRoleController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        personRoleDtoList = Stream.of(personRoleDto1, personRoleDto2).collect(Collectors.toList());;

        setupPersonRoleDto(personRoleDto1, 1, ROLE_FIRST_PERSON);
        setupPersonRoleDto(personRoleDto2, 2, ROLE_SECOND_PERSON);
    }

    @Test
    public void getAllTest() {
        when(personRoleService.getAll()).thenReturn(personRoleDtoList);
        ResponseEntity<List<PersonRoleDto>> responseEntity = personRoleController.getAll();

        assertEquals(personRoleDtoList, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());

    }

    @Test
    public void getByIdTest() {
        int id = 1;
        when(personRoleService.getById(id)).thenReturn(personRoleDto1);
        ResponseEntity<PersonRoleDto> responseEntity = personRoleController.getById(id);

        assertEquals(personRoleDto1, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    private void setupPersonRoleDto(PersonRoleDto personRoleDto, Integer id, String role) {
        when(personRoleDto.getId()).thenReturn(id);
        when(personRoleDto.getRole()).thenReturn(role);
    }
}
