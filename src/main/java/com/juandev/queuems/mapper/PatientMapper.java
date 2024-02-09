package com.juandev.queuems.mapper;

import com.juandev.queuems.dto.PatientDTO;
import com.juandev.queuems.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Mapping(target = "category", source = "categoryId")
    @Mapping(target = "service", source = "serviceId")
    @Mapping(target = "schedule", source = "scheduleId")
    Patient toEntity(PatientDTO dto);

    @Mapping(target = "categoryId", source = "category.categoryId")
    @Mapping(target = "serviceId", source = "service.serviceId")
    @Mapping(target = "scheduleId", source = "schedule.scheduleId")
    PatientDTO toDTO(Patient entity);
}
