package com.juandev.queuems.repository;

import com.juandev.queuems.model.ServiceModel;
import com.juandev.queuems.util.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceModel, Long> {
}
