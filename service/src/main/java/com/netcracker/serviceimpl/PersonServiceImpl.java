package com.netcracker.serviceimpl;

import com.netcracker.converter.PassportConverter;
import com.netcracker.converter.PersonConverter;
import com.netcracker.converter.PersonRoleConverter;
import com.netcracker.dto.PersonDto;
import com.netcracker.entity.Person;
import com.netcracker.entity.PersonRole;
import com.netcracker.exceptions.PersonNotFoundException;
import com.netcracker.repository.PersonRepository;
import com.netcracker.requestbody.PersonUpdate;
import com.netcracker.service.PassportService;
import com.netcracker.service.PersonRoleService;
import com.netcracker.service.PersonService;
import com.netcracker.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PassportService passportService;
    private final PersonConverter personConverter;
    private final PersonRoleService personRoleService;
    private final Validate validate;
    private final PasswordEncoder passwordEncoder;
    private final PassportConverter passportConverter;
    private final PersonRoleConverter personRoleConverter;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PassportService passportService,
                             PersonConverter personConverter, PersonRoleService personRoleService,
                             Validate validate, PasswordEncoder passwordEncoder, PassportConverter passportConverter, PersonRoleConverter personRoleConverter) {

        this.personRepository = personRepository;
        this.passportService = passportService;
        this.personConverter = personConverter;
        this.personRoleService = personRoleService;
        this.validate = validate;
        this.passwordEncoder = passwordEncoder;
        this.passportConverter = passportConverter;
        this.personRoleConverter = personRoleConverter;
    }

    @Override
    public PersonDto getById(Integer id) {

        Optional<Person> optionalPerson = personRepository.findById(id);

        if (optionalPerson.isPresent()) {
            return personConverter.converter(optionalPerson.get());
        }
        throw new PersonNotFoundException("No such person");


    }

    @Override
    public Integer save(PersonDto personDto) {

        Optional<Person> optionalPerson = personRepository.findByLogin(personDto.getLogin());
//
//        if (optionalPerson.isPresent()) {
//            throw new IllegalArgumentException("User with this login already exists");
//        } else if (validate.correctPhoneNumber(personDto.getPhoneNumber()) &&
//                validate.correctDate(personDto.getPassportDto().getDateExpire())) {
//            PersonRole personRole = personRoleService.getByRole("user");
            Person person = personConverter.converter(personDto);

//            person.setPassport(passportConverter.converter(
//                    passportService.save(personDto.getPassportDto())));
//            person.setPersonRole(personRole);
//            person.setPassword(passwordEncoder.encode(personDto.getPassword()));
            return personRepository.save(person).getId();
//        }
//        throw new IllegalArgumentException("Wrong data");
    }

    @Override
    public PersonDto update(PersonUpdate personUpdate) {
        PersonRole personRole = personRoleConverter.converter(personRoleService.getById(personUpdate.getPersonRoleId()));
        PersonDto personDto = this.getById(personUpdate.getPersonId());
        Person person = personConverter.converter(personDto);
        person.setPersonRole(personRole);
        return personConverter.converter(personRepository.save(person));
    }

    @Override
    public PersonDto findByLogin(String login) {
        Optional<Person> optionalPerson = personRepository.findByLogin(login);
        if (optionalPerson.isPresent()) {
            return personConverter.converter(optionalPerson.get());
        }
        throw new PersonNotFoundException("No such person");
    }

    @Override
    public void deleteById(Integer id) {
        personRepository.deleteById(id);
    }


}
