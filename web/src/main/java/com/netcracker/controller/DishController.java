package com.netcracker.controller;

import com.netcracker.dto.DishDto;
import com.netcracker.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api
@RestController
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @ApiOperation(value = "Gets all cheaper dishes")
    @GetMapping(value = "/filterbyprice")
    public ResponseEntity<List<DishDto>> getCheaperDish(@RequestParam("price") int price) {
       // log.info("getCheaperDish , DishController");
        return new ResponseEntity<>(dishService.getCheaperDish(price), HttpStatus.OK);
    }

    @ApiOperation(value = "Gets all  dishes")
    @GetMapping()
    public ResponseEntity<List<DishDto>> getAll() {
       // log.info("getAll , DishController ");
        return new ResponseEntity<>(dishService.getAll(), HttpStatus.OK);
    }
}
