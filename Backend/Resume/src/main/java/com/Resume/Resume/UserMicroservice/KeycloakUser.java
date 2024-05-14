package com.Resume.Resume.UserMicroservice;

import jakarta.persistence.Column;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class KeycloakUser {
    @NotNull(message = "username must not be null")
    @NotBlank(message = "username can't be blank")
    @NotEmpty(message = "username can't be empty")
    @Column(unique = true)
    private String username;
    @NotNull(message = "Email must not be null")
    @NotBlank(message = "Email can't be blank")
    @NotEmpty(message = "Email can't be empty")
    private String email;

    @NotNull(message = "firstname must not be null")
    @NotBlank(message = "firstname can't be blank")
    @NotEmpty(message = "firstname can't be empty")
    private String firstName;
    @NotNull(message = "lastname must not be null")
    @NotBlank(message = "lastname can't be blank")
    @NotEmpty(message = "lastname can't be empty")
    private String lastName;
    private String role;
    @NotNull(message = "lastname must not be null")
    @NotBlank(message = "lastname can't be blank")
    @NotEmpty(message = "lastname can't be empty")
    private String cin;
}

