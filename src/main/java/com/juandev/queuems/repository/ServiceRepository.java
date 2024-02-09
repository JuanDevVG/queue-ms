package com.juandev.queuems.repository;

import com.juandev.queuems.model.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceModel, Long> {
}
