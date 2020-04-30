package com.netcracker.controller;


import com.netcracker.dto.CommentDto;
import com.netcracker.service.ApartmentService;
import com.netcracker.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final ApartmentService apartmentService;


    public CommentController(CommentService commentService, ApartmentService apartmentService) {
        this.commentService = commentService;
        this.apartmentService = apartmentService;
    }

    @ApiOperation(value = "Creates comment")
    @PostMapping()
    ResponseEntity<Void> save(@RequestBody @Validated CommentDto commentDto) {
       // log.info("save , CommentController");
        commentService.save(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes comment")
    @DeleteMapping()
    ResponseEntity<Void> deleteById(@RequestParam Integer id) {
        //log.info("deleteById , CommentController");
        commentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @ApiOperation(value = "Gets all comments by apartment's id ")
    @GetMapping(value = "/filterbyapartmentid")
    ResponseEntity<List<CommentDto>> getAllByApartment_Id(@RequestParam("apartmentid") Integer apartmentId) {
        //log.info("getAllByApartment_Id , CommentController");
        apartmentService.getById(apartmentId);
        return new ResponseEntity<>(commentService.getAllByApartment_Id(apartmentId), HttpStatus.OK);
    }

    @ApiOperation(value = "Gets all comments by person's login")
    @GetMapping()
    ResponseEntity<List<CommentDto>> getAllByPerson() {
       // log.info("getAllByPerson , CommentController ");
        List<CommentDto> comments = commentService.getByPerson_Login();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
