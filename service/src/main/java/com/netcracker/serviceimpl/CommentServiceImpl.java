package com.netcracker.serviceimpl;

import com.netcracker.converter.ApartmentConverter;
import com.netcracker.converter.CommentConverter;
import com.netcracker.dto.CommentDto;
import com.netcracker.entity.Apartment;
import com.netcracker.entity.Comments;
import com.netcracker.entity.Person;
import com.netcracker.repository.CommentRepository;
import com.netcracker.service.ApartmentService;
import com.netcracker.service.CommentService;
import com.netcracker.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;
    private final ApartmentService apartmentService;
    private final PersonService personService;
    private final ApartmentConverter apartmentConverter;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentConverter commentConverter,
                              ApartmentService apartmentService, PersonService personService,
                              ApartmentConverter apartmentConverter) {
        this.commentRepository = commentRepository;
        this.commentConverter = commentConverter;
        this.apartmentService = apartmentService;
        this.personService = personService;
        this.apartmentConverter = apartmentConverter;
    }


    @Override
    public CommentDto save(CommentDto commentDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Apartment apartment = apartmentConverter.converter(
                apartmentService.getById(commentDto.getApartmentId()));
        Person person = personService.findByLogin(user.getUsername());
        Comments comment = commentConverter.converter(commentDto);
        comment.setApartment(apartment);
        comment.setPerson(person);
        return commentConverter.converter(commentRepository.save(comment));
    }

    @Override
    public List<CommentDto> getAllByApartment_Id(Integer apartmentId) {
        return commentRepository.getAllByApartment_Id(apartmentId)
                .stream()
                .map(comment -> commentConverter.converter(comment))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CommentDto comment = this.getById(id);
        Person person = personService.findByLogin(user.getUsername());
        if (comment.getPersonLogin().equals(user.getUsername())) {
            commentRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("You can't delete this comment ");
        }
    }

    @Override
    public CommentDto getById(Integer id) {
        Optional<Comments> optionalComments = commentRepository.findById(id);
        if (optionalComments.isPresent()) {
            return commentConverter.converter(optionalComments.get());
        }
        throw new IllegalArgumentException("No such comment");
    }


    @Override
    public List<CommentDto> getByPerson_Login() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return commentRepository.getByPerson_Login(user.getUsername())
                .stream()
                .map(comments -> commentConverter.converter(comments))
                .collect(Collectors.toList());
    }
}
