package com.netcracker.converter;

import com.netcracker.dto.PersonRoleDto;
import com.netcracker.entity.PersonRole;
import org.springframework.stereotype.Component;

@Component
public class PersonRoleConverter {

    public PersonRole converter(PersonRoleDto personRoleDto) {
        PersonRole personRole = new PersonRole();
        personRole.setRole(personRoleDto.getRole());
        return personRole;
    }

    public PersonRoleDto converter(PersonRole personRole) {
        PersonRoleDto personRoleDto = new PersonRoleDto();
        personRoleDto.setId(personRole.getId());
        personRoleDto.setRole(personRole.getRole());
        return personRoleDto;
    }
}
