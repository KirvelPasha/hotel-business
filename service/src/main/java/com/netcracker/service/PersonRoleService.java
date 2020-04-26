package com.netcracker.service;


import com.netcracker.dto.PersonRoleDto;
import com.netcracker.entity.PersonRole;

import java.util.List;

public interface PersonRoleService {

    PersonRoleDto getById(Integer id);

    List<PersonRoleDto> getAll();

    PersonRoleDto getByRole(String role);

    PersonRoleDto save(PersonRoleDto personRoleDto);
}
