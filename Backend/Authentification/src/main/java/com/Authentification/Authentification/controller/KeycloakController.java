package com.Authentification.Authentification.controller;

import com.Authentification.Authentification.entity.KeycloakResponsableRh;
import com.Authentification.Authentification.entity.KeycloakUser;
import com.Authentification.Authentification.entity.KeycloakUserDto;
import com.Authentification.Authentification.services.KeycloakService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping( "/user")
@CrossOrigin("*")
public class KeycloakController {
    private final KeycloakService service;
    @PostMapping("/signup")
    public String addUser(@Valid @RequestBody KeycloakUser keycloakUser){

        return service.addUser(keycloakUser);
    }
    @PostMapping("/AddResponsableRH")

    public String AddResponsableRH(@Valid @RequestBody KeycloakResponsableRh keycloakUser){

        return service.addResponsable_RH(keycloakUser);
    }
    @PostMapping("/AddAdmin")

    public String AddAdmin(@Valid @RequestBody KeycloakResponsableRh keycloakUser){

        return service.AddAdmin_Entreprise(keycloakUser);
    }
    @PostMapping("/admin/addUserByAdmin")
    public ResponseEntity<String> addUserByAdmin(@Valid @RequestBody KeycloakUser keycloakUser){
        service.addUserByAdmin(keycloakUser);
        return ResponseEntity.status(HttpStatus.OK).body("User Added Successfully");
    }
    @GetMapping("/ByName/{username}")
    public UserRepresentation getUserByName(@PathVariable("username") String username){
        UserRepresentation user = service.getUserByName(username);
        return user;
    }



    @GetMapping("/ById/{userId}")
    public UserRepresentation getUser(@PathVariable("userId") String userId){
        UserRepresentation user = service.getUser(userId);
        return user;
    }
    @GetMapping("/admin/findAll")
    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserRepresentation> getUsers(){
        List<UserRepresentation> user = service.getUsers();
        return user;
    }
    @PutMapping("/admin/addRoleToUser/{userName}")
    public String assignRealmRolesToUser(@RequestBody KeycloakUser keycloakUser, @PathVariable("userName") String userName){
        service.assignRealmRolesToUser(keycloakUser,userName);
        return "role assigned to user";
    }
    @GetMapping ("/admin/getCandidats")
    public List<KeycloakUserDto> getUsersWithRole(){
        List<KeycloakUserDto> user = service.getUsersWithRole();
        return user;
    }

    @PutMapping( "/admin/UpdateUserByAdmin/{userId}")
    public String updateUserByAdmin(@Valid @PathVariable("userId") String userId, @RequestBody KeycloakUser keycloakUser){

        service.updateUserByAmin(userId, keycloakUser);
        return "User Details Updated Successfully.";
    }
    @PutMapping( "/UpdateUser")
    public String updateUser(@Valid @RequestBody KeycloakUser keycloakUser, KeycloakAuthenticationToken authenticationToken){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) authenticationToken.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String userId = context.getToken().getSubject();
        System.out.println(userId);
        service.updateUser(userId, keycloakUser);
        return "User Details Updated Successfully.";
    }

    @DeleteMapping( "/deleteAccount/{userId}")
    public String deleteUserByAdmin(@PathVariable("userId") String userId){

        service.deleteUser(userId);
        return "User Deleted Successfully.";
    }
    @DeleteMapping( "/deleteMyAccount")
    public String deleteUser(KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String userId = context.getToken().getSubject();
        service.deleteMyAccount(userId);
        return "User Deleted Successfully.";
    }

}

