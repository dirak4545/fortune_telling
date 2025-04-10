package com.example.fortunetelling.repository;

import com.example.fortunetelling.entity.FortuneRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FortuneRequestRepository extends JpaRepository<FortuneRequest, Long> {
    List<FortuneRequest> findByName(String name);
}