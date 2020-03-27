package com.netcracker.controller;

import com.netcracker.dto.ApartmentTypeDto;
import com.netcracker.service.ApartmentTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api
@RestController
@RequestMapping("/apartmentypes")
public class ApartmentTypeController {

    private final ApartmentTypeService apartmentTypeService;

    @Autowired
    public ApartmentTypeController(ApartmentTypeService apartmentTypeService) {
        this.apartmentTypeService = apartmentTypeService;
    }

    @ApiOperation(value = "Gets apartment type by id")
    @GetMapping(value = "/")
    ResponseEntity<ApartmentTypeDto> getById(@RequestParam("id") Integer id) {
        return new ResponseEntity<>(apartmentTypeService.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Gets all  apartments types")
    @GetMapping()
    ResponseEntity<List<ApartmentTypeDto>> getAll() {
        log.info("getAll , ApartmentTypeController");
        return new ResponseEntity<>(apartmentTypeService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates apartment type")
    @PostMapping()
    ResponseEntity<ApartmentTypeDto> save(@Validated @RequestBody ApartmentTypeDto apartmentTypeDto) {
        log.info("save , ApartmentTypeController");
        return new ResponseEntity<>(apartmentTypeService.save(apartmentTypeDto), HttpStatus.CREATED);
    }


}

