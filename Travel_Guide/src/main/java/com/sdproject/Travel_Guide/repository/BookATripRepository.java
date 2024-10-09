package com.sdproject.Travel_Guide.repository;

import com.sdproject.Travel_Guide.dto.BookATripDto;
import com.sdproject.Travel_Guide.entity.BookATrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookATripRepository extends JpaRepository<BookATrip, Long> {
    List<BookATrip> findAllByUserId(Long userId);
}
