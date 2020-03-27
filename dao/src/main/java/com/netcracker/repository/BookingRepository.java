package com.netcracker.repository;

import com.netcracker.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {


    List<Booking> getByPerson_Login(String login);

}
