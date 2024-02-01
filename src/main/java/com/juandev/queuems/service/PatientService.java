package com.juandev.queuems.service;

import com.juandev.queuems.model.Category;
import com.juandev.queuems.model.Patient;
import com.juandev.queuems.model.ServiceModel;
import com.juandev.queuems.repository.CategoryRepository;
import com.juandev.queuems.repository.PatientRepository;
import com.juandev.queuems.repository.ServiceRepository;
import com.juandev.queuems.util.CategoryName;
import com.juandev.queuems.util.NewPatientRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Transactional
    public String addPatient(NewPatientRequest request) {
        Patient patient = new Patient();

        Category category = categoryRepository.findByCategoryName(request.getCategory());
        ServiceModel serviceModel = serviceRepository.findByServiceType(request.getService());

        if (patientRepository.findByIdentityCard(request.getIdentityCard()) == null){
            if (category != null){
                patient.setCategory(category);
            } else{
                Category newCategory = new Category();
                newCategory.setCategoryName(request.getCategory());
                patient.setCategory(categoryRepository.save(newCategory));
            }
            if (serviceModel != null){
                patient.setService(serviceModel);
            } else{
                ServiceModel newService = new ServiceModel();
                newService.setServiceType(request.getService());
                patient.setService(serviceRepository.save(newService));
            }


            patient.setIdentityCard(request.getIdentityCard());
            patientRepository.save(patient);
            return "Paciente agregado correctamente";
        } else {
            return "Error al agregar paciente";
        }
    }
}
