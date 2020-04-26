package com.netcracker.controller;

import com.netcracker.dto.PersonRoleDto;
import com.netcracker.entity.PersonRole;
import com.netcracker.service.PersonRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api
@RestController
@RequestMapping("/personroles")
public class PersonRoleController {

    private final PersonRoleService personRoleService;

    @Autowired
    public PersonRoleController(PersonRoleService personRoleService) {
        this.personRoleService = personRoleService;
    }

    @ApiOperation(value = "Gets by id")
    @GetMapping(value = "/")
    public ResponseEntity<PersonRoleDto> getById(@RequestParam("id") Integer id) {
       // log.info("getById ,vPersonRoleController");
        return new ResponseEntity<>(personRoleService.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Gets all person's roles")
    @GetMapping()
    public ResponseEntity<List<PersonRoleDto>> getAll() {
      //  log.info("getAll , PersonRoleController");
        return new ResponseEntity<>(personRoleService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates role")
    @PostMapping()
    public ResponseEntity<PersonRoleDto> save(@RequestBody PersonRoleDto personRoleDto) {
        return new ResponseEntity<>(personRoleService.save(personRoleDto), HttpStatus.CREATED);
    }

}
