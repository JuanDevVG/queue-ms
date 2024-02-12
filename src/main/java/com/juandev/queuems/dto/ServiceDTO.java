package com.juandev.queuems.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ServiceDTO {

    private Long serviceId;
    private String serviceType;
    private boolean active;

}
