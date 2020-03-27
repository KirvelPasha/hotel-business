package com.netcracker.repository;

import com.netcracker.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {

    List<Comments> getAllByApartment_Id(Integer apartmentId);

    List<Comments> getByPerson_Login(String login);

}
