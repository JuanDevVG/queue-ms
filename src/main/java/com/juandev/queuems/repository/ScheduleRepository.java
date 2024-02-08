package com.juandev.queuems.repository;

import com.juandev.queuems.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
}
