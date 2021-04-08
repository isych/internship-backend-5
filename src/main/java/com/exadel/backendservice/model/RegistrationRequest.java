package com.exadel.backendservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class RegistrationRequest {

    @NotEmpty
    @Size(min = 5, max = 20)
    private String login;

    @NotEmpty
    @Size(min = 1, max = 60)
    private String password;

    @NotEmpty
    private String role;

    @NotEmpty
    @Size(min = 1, max = 100)
    private String fio;

    @NonNull
    @Email
    private String email;
}