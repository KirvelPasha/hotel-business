package com.netcracker.serviceimpl;

import com.netcracker.converter.PassportConverter;
import com.netcracker.dto.PassportDto;
import com.netcracker.entity.Passport;
import com.netcracker.repository.PassportRepository;
import com.netcracker.service.PassportService;
import com.netcracker.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;
    private final PassportConverter passportConverter;
    private final PersonService personService;

    @Autowired
    public PassportServiceImpl(PassportRepository passportRepository,
                               PassportConverter passportConverter, @Lazy PersonService personService) {
        this.passportRepository = passportRepository;
        this.passportConverter = passportConverter;
        this.personService = personService;
    }

    @Override
    public PassportDto save(PassportDto passportDto) {
        Passport passport = passportConverter.converter(passportDto);
        return passportConverter.converter(passportRepository.save(passport));
    }

}
