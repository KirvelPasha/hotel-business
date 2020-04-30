package com.netcracker.serviceimpl;

import com.netcracker.converter.ApartmentConverter;
import com.netcracker.converter.ApartmentTypeConverter;
import com.netcracker.dto.ApartmentDto;
import com.netcracker.entity.Apartment;
import com.netcracker.entity.ApartmentTypes;
import com.netcracker.repository.ApartmentRepository;
import com.netcracker.service.ApartmentService;
import com.netcracker.service.ApartmentTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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
    @Cacheable("apartments")
    public ApartmentDto getById(Integer id) {
       // log.info("getting apartment by id: {}", id);

        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        if (optionalApartment.isPresent()) {
            String s = "dssfsdf";
            return apartmentConverter.converter(optionalApartment.get());
        }
        throw new IllegalArgumentException("No such apartment");
    }


    @Override
    public List<ApartmentDto> getAll() {
        //log.info("getting all apartments");
        return apartmentRepository.findAll()
                .stream()
                .map(apartmentConverter::converter)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentDto> getCheaperApartments(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price is less than 0");
        }
        return apartmentRepository.getCheaperApartments(price)
                .stream()
                .map(apartmentConverter::converter)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentDto> getApartmentsByCountPlaces(int countPlaces) {
        if (countPlaces <= 0) {
            throw new IllegalArgumentException("Count palces are less than 0");
        }
        return apartmentRepository.getApartmentsByCountPlaces(countPlaces)
                .stream()
                .map(apartmentConverter::converter)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentDto> getApartmentsByCountRooms(int countRooms) {
        if (countRooms <= 0) {
            throw new IllegalArgumentException("Count rooms are less than 0");
        }
        return apartmentRepository.getApartmentsByCountRooms(countRooms)
                .stream()
                .map(apartmentConverter::converter)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentDto> getApartmentsByCountPlacesAndCountRooms(int countPlaces, int countRooms) {
        if (countPlaces <= 0 || countRooms <= 0) {
            throw new IllegalArgumentException("Count palces or Count rooms are less than 0 ");
        }
        return apartmentRepository.getApartmentsByCountPlacesAndCountRooms(countPlaces, countRooms)
                .stream()
                .map(apartmentConverter::converter)
                .collect(Collectors.toList());
    }


    @Override
    @CachePut("apartments")
    public ApartmentDto save(ApartmentDto apartmentDto) {
        //log.info("save apartment");
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
    public List<ApartmentDto> getApartmentsByApartmentTypesId(Integer typeId) {
        apartmentTypeService.getById(typeId);
        return apartmentRepository.getApartmentsByApartmentTypes_Id(typeId)
                .stream()
                .map(apartmentConverter::converter)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "apartment")
    public void delete(Integer id) {
        //log.info("delete apartment by id: {}", id);
        this.getById(id);
        apartmentRepository.deleteById(id);
    }

}
