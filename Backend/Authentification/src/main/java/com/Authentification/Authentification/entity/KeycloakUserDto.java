package com.Authentification.Authentification.entity;

import com.Authentification.Authentification.utils.ValidPassword;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class KeycloakUserDto {
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
    @NotNull(message = "lastname must not be null")
    @NotBlank(message = "lastname can't be blank")
    @NotEmpty(message = "lastname can't be empty")
    private String cin;
}


