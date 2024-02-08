package com.juandev.queuems.service;

import com.juandev.queuems.Exception.ConflictCategoryException;
import com.juandev.queuems.model.Category;
import com.juandev.queuems.model.ServiceModel;
import com.juandev.queuems.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceModelService {

    @Autowired
    private ServiceRepository serviceRepository;

    public ServiceModel createService(ServiceModel newService) {
        return serviceRepository.save(newService);
    }

    public List<ServiceModel> getAllService() {
        return serviceRepository.findAll();
    }

    public ServiceModel updateService(ServiceModel updateService) {
        return serviceRepository.save(updateService);
    }
}

