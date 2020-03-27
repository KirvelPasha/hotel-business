package com.netcracker.converter;

import com.netcracker.dto.ApartmentTypeDto;
import com.netcracker.entity.ApartmentTypes;
import org.springframework.stereotype.Component;

@Component
public class ApartmentTypeConverter {

    public ApartmentTypes converter(ApartmentTypeDto apartmentTypeDto) {
        ApartmentTypes apartmentTypes = new ApartmentTypes();

        apartmentTypes.setId(apartmentTypeDto.getId());

        apartmentTypes.setType(apartmentTypeDto.getType());

        return apartmentTypes;
    }

    public ApartmentTypeDto converter(ApartmentTypes apartmentTypes) {
        ApartmentTypeDto apartmentTypeDto = new ApartmentTypeDto();

        apartmentTypeDto.setId(apartmentTypes.getId());

        apartmentTypeDto.setType(apartmentTypes.getType());

        return apartmentTypeDto;
    }

}
