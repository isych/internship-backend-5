package com.exadel.backendservice.dto;

import lombok.Data;

@Data
public class UserDto {
    private String login;
    private String password;
    private String role;
    private String fio;
    private String email;

}
