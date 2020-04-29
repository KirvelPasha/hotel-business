package com.netcracker.controller;


import com.netcracker.dto.DishOrderDto;
import com.netcracker.service.DishOrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/dishorders")
public class DishOrderController {


    private final DishOrderService dishOrderService;

    @Autowired
    public DishOrderController(DishOrderService dishOrderService) {
        this.dishOrderService = dishOrderService;
    }

    @ApiOperation(value = "Creates dish order")
    @PostMapping()
    ResponseEntity<DishOrderDto> save(@RequestBody DishOrderDto dishOrderDto) {
        //log.info("save , DishOrderController");
        return new ResponseEntity<>(dishOrderService.save(dishOrderDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Gets all  dish order by person's login")
    @GetMapping()
    List<DishOrderDto> getByPerson_Login() {
        //log.info("getByPerson_Login , DishOrderController ");
        return dishOrderService.getByPerson_Login();

    }

    @ApiOperation(value = "Deletes dish order")
    @DeleteMapping()
    ResponseEntity<Void> deleteById(@RequestParam("id") Integer id) {
       // log.info("deleteById , DishOrderController");
        dishOrderService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
