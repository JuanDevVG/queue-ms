package com.juandev.queuems.service;

import com.juandev.queuems.Exception.InvalidCategoryOrServiceException;
import com.juandev.queuems.Exception.ConflictIdentityCardException;
import com.juandev.queuems.model.Category;
import com.juandev.queuems.model.Patient;
import com.juandev.queuems.model.ServiceModel;
import com.juandev.queuems.repository.CategoryRepository;
import com.juandev.queuems.repository.PatientRepository;
import com.juandev.queuems.repository.ServiceRepository;
import com.juandev.queuems.util.CategoryName;
import com.juandev.queuems.util.NewPatientRequest;
import com.juandev.queuems.util.ServiceType;
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
    public Patient savePatient(NewPatientRequest request) {
        Patient patient = new Patient();

        //Validar que la categoria recibida exista en el enum CategoryName Y ServiceType
        CategoryName categoryName = CategoryName.valueOf(request.getCategory().name());;
        ServiceType serviceType = ServiceType.valueOf(request.getService().name());

        if (categoryName.equals(request.getCategory()) && serviceType.equals(request.getService())) {

            if (patientRepository.findByIdentityCard(request.getIdentityCard()) == null){

                //Verificando que category y service existan en la base de datos
                Category category = categoryRepository.findByCategoryName(request.getCategory());
                ServiceModel serviceModel = serviceRepository.findByServiceType(request.getService());

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

                return patientRepository.save(patient);
            } else {
                throw new ConflictIdentityCardException("Ya existe un paciente con la identificación proporcionada");
            }
        } else {
            throw new InvalidCategoryOrServiceException("La categoría o el servicio proporcionados no coinciden con los valores esperados");
        }
    }
}
