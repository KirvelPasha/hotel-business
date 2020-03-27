package com.netcracker.serviceimpl;

import com.netcracker.converter.ApartmentTypeConverter;
import com.netcracker.dto.ApartmentTypeDto;
import com.netcracker.entity.ApartmentTypes;
import com.netcracker.repository.ApartmentTypeRepository;
import com.netcracker.service.ApartmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApartmentTypeServiceImpl implements ApartmentTypeService {

    private final ApartmentTypeRepository apartmentTypeRepository;
    private final ApartmentTypeConverter apartmentTypeConverter;

    @Autowired
    public ApartmentTypeServiceImpl(ApartmentTypeRepository apartmentTypeRepository,
                                    ApartmentTypeConverter apartmentTypeConverter) {
        this.apartmentTypeRepository = apartmentTypeRepository;
        this.apartmentTypeConverter = apartmentTypeConverter;
    }


    @Override
    public List<ApartmentTypeDto> getAll() {
        return apartmentTypeRepository.findAll()
                .stream()
                .map(apartmentTypes -> apartmentTypeConverter.converter(apartmentTypes))
                .collect(Collectors.toList());
    }

    @Override
    public ApartmentTypeDto getById(Integer id) {
        Optional<ApartmentTypes> optionalApartmentTypes = apartmentTypeRepository.findById(id);
        if (optionalApartmentTypes.isPresent()) {
            return apartmentTypeConverter.converter(optionalApartmentTypes.get());
        }
        throw new IllegalArgumentException("No such apartment type");
    }

    @Override
    public ApartmentTypeDto save(ApartmentTypeDto apartmentTypeDto) {
        if (apartmentTypeDto.getId() != null) {
            if (apartmentTypeRepository.findById(apartmentTypeDto.getId()).isPresent()) {
                throw new IllegalArgumentException("Apartment Type  with this id already exists");
            }
        }
        ApartmentTypes apartmentTypes = apartmentTypeConverter.converter(apartmentTypeDto);
        return apartmentTypeConverter.converter(apartmentTypeRepository.save(apartmentTypes));
    }

    @Override
    public void deleteById(Integer id) {
        this.getById(id);
        apartmentTypeRepository.deleteById(id);
    }
}
