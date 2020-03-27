package com.netcracker.repository;

import com.netcracker.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Integer> {

    Passport getPassportByPerson_Id(Integer personId);
}
