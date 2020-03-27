package com.netcracker.service;


import com.netcracker.dto.PersonRoleDto;
import com.netcracker.entity.PersonRole;

import java.util.List;

public interface PersonRoleService {

    PersonRole getById(Integer id);

    List<PersonRole> getAll();

    PersonRole getByRole(String role);

    PersonRoleDto save(PersonRoleDto personRoleDto);
}
