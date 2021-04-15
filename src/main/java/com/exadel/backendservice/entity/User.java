package com.exadel.backendservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Size(min = 5, max = 20)
    private String login;

    @NonNull
    @Size(min = 1, max = 60)
    private String password;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role roleEntity;

    @NonNull
    @Size(min = 1, max = 100)
    private String fio;

    @NonNull
    @Email
    private String email;

    public User(String login,String password, Role roleEntity, String fio, String email) {
        this.login = login;
        this.password = password;
        this.roleEntity = roleEntity;
        this.fio = fio;
        this.email = email;
    }
}
