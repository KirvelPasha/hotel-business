package com.netcracker.service;


import com.netcracker.dto.ApartmentTypeDto;

import java.util.List;

public interface ApartmentTypeService {

    List<ApartmentTypeDto> getAll();

    ApartmentTypeDto getById(Integer id);

    ApartmentTypeDto save(ApartmentTypeDto apartmentTypeDto);

    void deleteById(Integer id);


}
