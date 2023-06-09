package com.ruzhkov.jwt.backend.repositories;

import com.ruzhkov.jwt.backend.entites.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Integer> {
}
