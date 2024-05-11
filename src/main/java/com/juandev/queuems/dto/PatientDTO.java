package com.juandev.queuems.dto;

import com.juandev.queuems.model.Category;
import com.juandev.queuems.model.ServiceModel;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PatientDTO {
    private Long patientId;
    private String identityCard;
    private String name;
    private String lastname;
    private Category category;
    private boolean active;
}
