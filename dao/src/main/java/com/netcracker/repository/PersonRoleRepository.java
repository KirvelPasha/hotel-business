package com.netcracker.repository;

import com.netcracker.entity.PersonRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRoleRepository extends JpaRepository<PersonRole, Integer> {
    PersonRole getByRole(String role);
}
