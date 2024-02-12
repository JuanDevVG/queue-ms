package com.juandev.queuems.repository;

import com.juandev.queuems.model.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceModel, Long> {
    boolean existsByServiceType(String serviceType);

    Optional<ServiceModel> findByServiceType(String service);
}
