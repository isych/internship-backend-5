package com.exadel.backendservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDtoWithId {
    private Integer id;
    private String fio;
    private String role;
    private String email;
}
