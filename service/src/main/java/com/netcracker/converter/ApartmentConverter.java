package com.netcracker.converter;


import com.netcracker.dto.ApartmentDto;
import com.netcracker.entity.Apartment;
import org.springframework.stereotype.Component;

@Component
public class ApartmentConverter {

    public Apartment converter(ApartmentDto apartmentDto) {
        Apartment apartment = new Apartment();

        apartment.setId(apartmentDto.getId());

        apartment.setCountPlaces(apartmentDto.getCountPlaces());

        apartment.setCountRooms(apartmentDto.getCountRooms());

        apartment.setNumber(apartmentDto.getNumber());

        apartment.setPrice(apartmentDto.getPrice());

        return apartment;
    }

    public ApartmentDto converter(Apartment apartment) {
        ApartmentDto apartmentDto = new ApartmentDto();

        apartmentDto.setId(apartment.getId());

        apartmentDto.setCountPlaces(apartment.getCountPlaces());

        apartmentDto.setCountRooms(apartment.getCountRooms());

        apartmentDto.setNumber(apartment.getNumber());

        apartmentDto.setPrice(apartment.getNumber());

        apartmentDto.setTypeId(apartment.getApartmentTypes().getId());

        return apartmentDto;
    }
}
