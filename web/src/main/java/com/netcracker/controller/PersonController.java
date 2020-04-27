package com.netcracker.controller;

import com.netcracker.dto.PersonDto;
import com.netcracker.requestbody.PersonUpdate;
import com.netcracker.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @ApiOperation(value = "Gets person by id")
    @GetMapping()
    ResponseEntity<PersonDto> getById(@RequestParam Integer id) {
        log.info("getById , PersonController");
        return new ResponseEntity<>(personService.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates person")
    @PostMapping()
    ResponseEntity<Integer> savePerson(@RequestBody PersonDto personDto) {
        log.info("savePerson , PersonController");
        return new ResponseEntity<>(personService.save(personDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Updates person")
    @PatchMapping()
    public ResponseEntity<PersonDto> update(@RequestBody PersonUpdate personUpdate) {
        log.info("update , PersonController");
        return new ResponseEntity<>(personService.update(personUpdate), HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam Integer id) {
        personService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
