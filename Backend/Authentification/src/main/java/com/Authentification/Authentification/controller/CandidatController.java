package com.Authentification.Authentification.controller;

import com.Authentification.Authentification.entity.KeycloakUser;
import com.Authentification.Authentification.entity.KeycloakUserDto;
import com.Authentification.Authentification.services.KeycloakService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping( "/user")
@CrossOrigin("*")
public class CandidatController {
    KeycloakService service;
    @GetMapping("/admin/findAllCandidats")
    public List<KeycloakUserDto> getCandidatUsers(){
        List<KeycloakUserDto> user = service.getUsersWithRole();
        return user;
    }

}
