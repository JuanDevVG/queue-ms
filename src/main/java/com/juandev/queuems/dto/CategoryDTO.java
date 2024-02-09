package com.juandev.queuems.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
    private boolean active;
}
