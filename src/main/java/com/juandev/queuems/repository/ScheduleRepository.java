package com.juandev.queuems.repository;

import com.juandev.queuems.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
    Optional<List<Schedule>> findByStatus(String status);
}
