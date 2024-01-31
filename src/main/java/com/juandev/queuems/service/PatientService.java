package com.juandev.queuems.service;

import com.juandev.queuems.model.Category;
import com.juandev.queuems.model.Patient;
import com.juandev.queuems.model.ServiceModel;
import com.juandev.queuems.repository.CategoryRepository;
import com.juandev.queuems.repository.PatientRepository;
import com.juandev.queuems.repository.ServiceRepository;
import com.juandev.queuems.util.NewPatientRequest;
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

    public Patient addPatient(NewPatientRequest newPatient) {
        ServiceModel serviceModel = serviceRepository.findByServiceType(newPatient.getService());
        Category category = categoryRepository.findByCategoryName(newPatient.getCategory());

        Patient patientAdd = new Patient();

        patientAdd.setIdentityCard(newPatient.getIdentityCard());
        patientAdd.setService(serviceModel);
        patientAdd.setCategory(category);

        return patientRepository.save(patientAdd);

    }
}
