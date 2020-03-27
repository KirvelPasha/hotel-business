package com.netcracker.serviceimpl;

import com.netcracker.converter.ApartmentConverter;
import com.netcracker.converter.ApartmentTypeConverter;
import com.netcracker.dto.ApartmentDto;
import com.netcracker.entity.Apartment;
import com.netcracker.entity.ApartmentTypes;
import com.netcracker.repository.ApartmentRepository;
import com.netcracker.service.ApartmentService;
import com.netcracker.service.ApartmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final ApartmentTypeService apartmentTypeService;
    private final ApartmentConverter apartmentConverter;
    private final ApartmentTypeConverter apartmentTypeConverter;


    @Autowired
    public ApartmentServiceImpl(ApartmentRepository apartmentRepository,
                                ApartmentTypeService apartmentTypeService, ApartmentConverter apartmentConverter, ApartmentTypeConverter apartmentTypeConverter) {
        this.apartmentRepository = apartmentRepository;
        this.apartmentTypeService = apartmentTypeService;
        this.apartmentConverter = apartmentConverter;
        this.apartmentTypeConverter = apartmentTypeConverter;
    }


    @Override
    public List<ApartmentDto> getAll() {
        return apartmentRepository.findAll()
                .stream()
                .map(apartment -> apartmentConverter.converter(apartment))
                .collect(Collectors.toList());
    }

    @Override
    public ApartmentDto getById(Integer id) {
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        if (optionalApartment.isPresent()) {
            return apartmentConverter.converter(optionalApartment.get());
        }
        throw new IllegalArgumentException("No such apartment");
    }

    @Override
    public List<ApartmentDto> getCheaperApartments(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price is less than 0");
        }
        return apartmentRepository.getCheaperApartments(price)
                .stream()
                .map(apartment -> apartmentConverter.converter(apartment))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentDto> getApartmentsByCountPlaces(int countPlaces) {
        if (countPlaces <= 0) {
            throw new IllegalArgumentException("Count palces are less than 0");
        }
        return apartmentRepository.getApartmentsByCountPlaces(countPlaces)
                .stream()
                .map(apartment -> apartmentConverter.converter(apartment))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentDto> getApartmentsByCountRooms(int countRooms) {
        if (countRooms <= 0) {
            throw new IllegalArgumentException("Count rooms are less than 0");
        }
        return apartmentRepository.getApartmentsByCountRooms(countRooms)
                .stream()
                .map(apartment -> apartmentConverter.converter(apartment))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentDto> getApartmentsByCountPlacesAndCountRooms(int countPlaces, int countRooms) {
        if (countPlaces <= 0 || countRooms <= 0) {
            throw new IllegalArgumentException("Count palces or Count rooms are less than 0 ");
        }
        return apartmentRepository.getApartmentsByCountPlacesAndCountRooms(countPlaces, countRooms)
                .stream()
                .map(apartment -> apartmentConverter.converter(apartment))
                .collect(Collectors.toList());
    }


    @Override
    public ApartmentDto save(ApartmentDto apartmentDto) {
        if (apartmentDto.getId() != null) {
            if (apartmentRepository.findById(apartmentDto.getId()).isPresent()) {
                throw new IllegalArgumentException("Apartment  with this id already exists");
            }
        }
        ApartmentTypes apartmentTypes = apartmentTypeConverter.converter(
                apartmentTypeService.getById(apartmentDto.getTypeId()));
        Apartment apartment = apartmentConverter.converter(apartmentDto);
        apartment.setApartmentTypes(apartmentTypes);
        return apartmentConverter.converter(apartmentRepository.save(apartment));
    }

    @Override
    public List<ApartmentDto> getApartmentsByApartmentTypes_Id(Integer typeId) {
        apartmentTypeService.getById(typeId);
        return apartmentRepository.getApartmentsByApartmentTypes_Id(typeId)
                .stream()
                .map(apartment -> apartmentConverter.converter(apartment))
                .collect(Collectors.toList());
    }


}
