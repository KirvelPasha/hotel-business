package com.netcracker.repository;

import com.netcracker.entity.Produce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduceRepository extends JpaRepository<Produce, Integer> {
}
