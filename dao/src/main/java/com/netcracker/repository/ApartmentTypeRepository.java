package com.netcracker.repository;


import com.netcracker.entity.ApartmentTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApartmentTypeRepository extends JpaRepository<ApartmentTypes, Integer> {

    Optional<ApartmentTypes> getByType(String type);
}
