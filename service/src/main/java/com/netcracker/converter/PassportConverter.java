package com.netcracker.converter;


import com.netcracker.dto.PassportDto;
import com.netcracker.entity.Passport;
import org.springframework.stereotype.Component;

@Component
public class PassportConverter {


    public Passport converter(PassportDto passportDto) {
        Passport passport = new Passport();

        passport.setId(passportDto.getId());

        passport.setDateExpire(passportDto.getDateExpire());

        passport.setSerialNumber(passportDto.getSerialNumber());

        return passport;
    }


    public PassportDto converter(Passport passport) {
        PassportDto passportDto = new PassportDto();

        passportDto.setId(passport.getId());

        passportDto.setDateExpire(passport.getDateExpire());

        passportDto.setSerialNumber(passport.getSerialNumber());

        return passportDto;
    }


}
