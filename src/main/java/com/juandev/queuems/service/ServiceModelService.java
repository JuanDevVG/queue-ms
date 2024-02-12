package com.juandev.queuems.service;

import com.juandev.queuems.Exception.ConflictRecordException;
import com.juandev.queuems.Exception.RecordGetNotFoundException;
import com.juandev.queuems.dto.ScheduleDTO;
import com.juandev.queuems.dto.ServiceDTO;
import com.juandev.queuems.model.Schedule;
import com.juandev.queuems.model.ServiceModel;
import com.juandev.queuems.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceModelService {

    @Autowired
    private ServiceRepository serviceRepository;

    public void saveService(ServiceDTO serviceDTO) {

        Optional<ServiceModel> service = serviceRepository.findByServiceType(serviceDTO.getServiceType());

        if (service.isPresent()){
            throw new ConflictRecordException("El servicio "+serviceDTO.getServiceType()+" ya se encuentra creado");
        } else {

            //Mapear el serviceDTO en una entidad ServiceModel
            ServiceModel newService = ServiceModel.builder()
                    .serviceType(serviceDTO.getServiceType())
                    .active(serviceDTO.isActive())
                    .build();

            //Guardar el servicio en la base de datos
            serviceRepository.save(newService);
        }
    }

    public List<ServiceDTO> getAllService() {
        List<ServiceModel> services = serviceRepository.findAll();

        if (!services.isEmpty()) {
            List<ServiceDTO> serviceDTOList = new ArrayList<>();

            for (ServiceModel service : services) {

                //Mapea datos de services en lista de serviceDTO
                ServiceDTO serviceDTO = ServiceDTO.builder()
                        .serviceId(service.getServiceId())
                        .serviceType(service.getServiceType())
                        .active(service.isActive())
                        .build();

                //Agrega el serviceDTO creado a la lista de serviceDTOList
                serviceDTOList.add(serviceDTO);

            }
            return serviceDTOList;
        } else {
            throw new RecordGetNotFoundException("Aun no se encuentran servicios registrados en la base de datos");
        }
    }
    public void updateService(ServiceDTO serviceDTO) {
        //Verifica que el turno se encuentre en la base de datos
        ServiceModel service = serviceRepository.findById(serviceDTO.getServiceId())
                .orElseThrow(() -> new RecordGetNotFoundException("El servicio con id: " + serviceDTO.getServiceId()+ " No se ecuentra registrado"));

        service.setServiceType(serviceDTO.getServiceType());
        service.setActive(serviceDTO.isActive());

        serviceRepository.save(service);
    }
}

