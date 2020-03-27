package com.netcracker.repository;


import com.netcracker.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

    @Query("select a from Apartment a where a.price <= :price")
    List<Apartment> getCheaperApartments(@Param("price") int price);

    @Query("select a from Apartment a where a.countPlaces = :countPlaces")
    List<Apartment> getApartmentsByCountPlaces(@Param("countPlaces") int countPlaces);

    @Query("select a from Apartment a where a.countRooms = :countRooms")
    List<Apartment> getApartmentsByCountRooms(@Param("countRooms") int countRooms);

    List<Apartment> getApartmentsByCountPlacesAndCountRooms(int countPlaces, int countRooms);

    List<Apartment> getApartmentsByApartmentTypes_Id(Integer typeId);
}
