package com.juandev.queuems.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
    private String categoryDescription;
    private boolean active;
}
