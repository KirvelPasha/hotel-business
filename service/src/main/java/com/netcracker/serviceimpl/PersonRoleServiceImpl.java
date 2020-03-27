package com.netcracker.serviceimpl;


import com.netcracker.converter.PersonRoleConverter;
import com.netcracker.dto.PersonRoleDto;
import com.netcracker.entity.PersonRole;
import com.netcracker.repository.PersonRoleRepository;
import com.netcracker.service.PersonRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonRoleServiceImpl implements PersonRoleService {

    private final PersonRoleRepository personRoleRepository;
    private final PersonRoleConverter personRoleConverter;

    @Autowired
    public PersonRoleServiceImpl(PersonRoleRepository personRoleRepository,
                                 PersonRoleConverter personRoleConverter) {
        this.personRoleRepository = personRoleRepository;
        this.personRoleConverter = personRoleConverter;
    }

    @Override
    public PersonRole getById(Integer id) {
        Optional<PersonRole> optionalPersonRole = personRoleRepository.findById(id);
        if (optionalPersonRole.isPresent()) {
            return optionalPersonRole.get();
        }
        throw new IllegalArgumentException("No such person role");
    }

    @Override
    public List<PersonRole> getAll() {
        return personRoleRepository.findAll();
    }

    @Override
    public PersonRole getByRole(String role) {
        return personRoleRepository.getByRole(role);
    }

    @Override
    public PersonRoleDto save(PersonRoleDto personRoleDto) {
        PersonRole personRole = personRoleConverter.converter(personRoleDto);
        return personRoleConverter.converter(personRoleRepository.save(personRole));
    }
}
