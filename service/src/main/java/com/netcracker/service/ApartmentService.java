package com.netcracker.service;

import com.netcracker.dto.ApartmentDto;

import java.util.List;


public interface ApartmentService {

    List<ApartmentDto> getAll();

    ApartmentDto getById(Integer id);

    List<ApartmentDto> getCheaperApartments(int price);

    List<ApartmentDto> getApartmentsByCountPlaces(int countPlaces);

    List<ApartmentDto> getApartmentsByCountRooms(int countRooms);

    List<ApartmentDto> getApartmentsByCountPlacesAndCountRooms(int countPlaces, int countRooms);

    ApartmentDto save(ApartmentDto apartmentDto);

    List<ApartmentDto> getApartmentsByApartmentTypes_Id(Integer typeId);


}
