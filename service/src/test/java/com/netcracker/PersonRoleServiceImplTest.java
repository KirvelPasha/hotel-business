package com.netcracker;

import com.netcracker.converter.PersonRoleConverter;
import com.netcracker.dto.ApartmentTypeDto;
import com.netcracker.dto.PersonRoleDto;
import com.netcracker.entity.ApartmentTypes;
import com.netcracker.entity.PersonRole;
import com.netcracker.repository.PersonRoleRepository;
import com.netcracker.serviceimpl.PersonRoleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PersonRoleServiceImplTest {

    private final String ROLE_FIRST_PERSON = "role1";
    private final String ROLE_SECOND_PERSON = "role2";

    @Mock
    PersonRole personRole1;
    @Mock
    PersonRole personRole2;

    @Mock
    PersonRoleDto personRoleDto1;
    @Mock
    PersonRoleDto personRoleDto2;

    @Mock
    PersonRoleRepository personRoleRepository;

    @Mock
    PersonRoleConverter personRoleConverter;

    List<PersonRole> personRoleList;
    List<PersonRoleDto> personRoleDtoList;

    @InjectMocks
    PersonRoleServiceImpl personRoleService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        personRoleList = Stream.of(personRole1, personRole2).collect(Collectors.toList());
        personRoleDtoList = Stream.of(personRoleDto1, personRoleDto2).collect(Collectors.toList());

        setupPersonRole(personRole1, 1, ROLE_FIRST_PERSON);
        setupPersonRole(personRole2, 2, ROLE_SECOND_PERSON);

        setupPersonRoleDto(personRoleDto1, 1, ROLE_FIRST_PERSON);
        setupPersonRoleDto(personRoleDto2, 2, ROLE_SECOND_PERSON);
    }

    @Test
    public void getAllTest(){
        when(personRoleRepository.findAll()).thenReturn(personRoleList);
        assertEquals(personRoleList, personRoleService.getAll());
    }

    @Test
    public void getByIdTest(){
        when(personRoleRepository.findById(1)).thenReturn(Optional.of(personRole1));
        assertEquals(personRole1, personRoleService.getById(1));
    }

    @Test
    public void getByRoleTest(){
        when(personRoleRepository.getByRole(ROLE_FIRST_PERSON)).thenReturn(personRole1);
        assertEquals(personRole1, personRoleService.getByRole(ROLE_FIRST_PERSON));
    }

    private void setupPersonRole(PersonRole personRole, Integer id, String role) {
        when(personRole.getId()).thenReturn(id);
        when(personRole.getRole()).thenReturn(role);
    }

    private void setupPersonRoleDto(PersonRoleDto personRoleDto, Integer id, String role) {
        when(personRoleDto.getId()).thenReturn(id);
        when(personRoleDto.getRole()).thenReturn(role);
    }

    private void setupConverterPersonRole(PersonRole personRole, PersonRoleDto personRoleDto) {
        when(personRoleConverter.converter(personRole)).thenReturn(personRoleDto);
    }
}
