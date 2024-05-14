package com.Authentification.Authentification.services;

import com.Authentification.Authentification.Config.KeycloakConfig;
import com.Authentification.Authentification.entity.KeycloakResponsableRh;
import com.Authentification.Authentification.entity.KeycloakUser;
import com.Authentification.Authentification.entity.KeycloakUserDto;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.management.Attribute;
import java.util.*;

import static com.Authentification.Authentification.Config.Credentials.createPasswordCredentials;

@Service
@AllArgsConstructor
@Slf4j
@EnableScheduling
public class KeycloakService {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    public String addUser(KeycloakUser keycloakUser) {
        UsersResource usersResource = KeycloakConfig.getUserResource();
        RolesResource rolesResource = KeycloakConfig.getRealmResource().roles();
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(keycloakUser.getFirstName());
        userRepresentation.setLastName(keycloakUser.getLastName());
        userRepresentation.setEmail(keycloakUser.getEmail());
        userRepresentation.setUsername(keycloakUser.getUsername());
        userRepresentation.setRealmRoles(Collections.singletonList("Candidat"));
        Map<String, List<String>> attributes = new HashMap<>();
        userRepresentation.setAttributes(attributes);
        attributes.put("cin", Collections.singletonList(keycloakUser.getCin()));
        userRepresentation.setAttributes(attributes);
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
         Response response = usersResource.create(userRepresentation);
            String path = response.getLocation().getPath();
            String userId = path.substring(path.lastIndexOf("/") + 1);

            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(keycloakUser.getPassword());

            usersResource.get(userId).resetPassword(credentialRepresentation);

            RoleRepresentation role = rolesResource.get("Candidat").toRepresentation();
            usersResource.get(userId).roles().realmLevel().add(Collections.singletonList(role));




            return "User added.";


    }
    public String addResponsable_RH(KeycloakResponsableRh keycloakUser) {
        UsersResource usersResource = KeycloakConfig.getUserResource();
        RolesResource rolesResource = KeycloakConfig.getRealmResource().roles();
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(keycloakUser.getFirstName());
        userRepresentation.setLastName(keycloakUser.getLastName());
        userRepresentation.setEmail(keycloakUser.getEmail());
        userRepresentation.setUsername(keycloakUser.getUsername());
        userRepresentation.setRealmRoles(Collections.singletonList("Responsable RH"));
        Map<String, List<String>> attributes = new HashMap<>();
        userRepresentation.setAttributes(attributes);
        attributes.put("cin", Collections.singletonList(keycloakUser.getCin()));
        attributes.put("id_Entreprise", Collections.singletonList(keycloakUser.getId_Entreprise()));
        attributes.put("nom_Entreprise", Collections.singletonList(keycloakUser.getNom_Entreprise()));

        userRepresentation.setAttributes(attributes);

        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        Response response = usersResource.create(userRepresentation);
        String path = response.getLocation().getPath();
        String userId = path.substring(path.lastIndexOf("/") + 1);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(keycloakUser.getPassword());

        usersResource.get(userId).resetPassword(credentialRepresentation);

        RoleRepresentation role = rolesResource.get("Responsable RH").toRepresentation();
        usersResource.get(userId).roles().realmLevel().add(Collections.singletonList(role));




        return "Responsable_RH added.";


    }
    public String AddAdmin_Entreprise(KeycloakResponsableRh keycloakUser) {
        UsersResource usersResource = KeycloakConfig.getUserResource();
        RolesResource rolesResource = KeycloakConfig.getRealmResource().roles();
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(keycloakUser.getFirstName());
        userRepresentation.setLastName(keycloakUser.getLastName());
        userRepresentation.setEmail(keycloakUser.getEmail());
        userRepresentation.setUsername(keycloakUser.getUsername());
        userRepresentation.setRealmRoles(Collections.singletonList("Admin Entreprise"));
        Map<String, List<String>> attributes = new HashMap<>();
        userRepresentation.setAttributes(attributes);
        attributes.put("cin", Collections.singletonList(keycloakUser.getCin()));
        attributes.put("id_Entreprise", Collections.singletonList(keycloakUser.getId_Entreprise()));
        attributes.put("nom_Entreprise", Collections.singletonList(keycloakUser.getNom_Entreprise()));

        userRepresentation.setAttributes(attributes);
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        Response response = usersResource.create(userRepresentation);
        String path = response.getLocation().getPath();
        String userId = path.substring(path.lastIndexOf("/") + 1);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(keycloakUser.getPassword());

        usersResource.get(userId).resetPassword(credentialRepresentation);

        RoleRepresentation role = rolesResource.get("Admin Entreprise").toRepresentation();
        usersResource.get(userId).roles().realmLevel().add(Collections.singletonList(role));




        return "Admin Entreprise added.";


    }




    public String addUserByAdmin(KeycloakUser keycloakUser) {
        UsersResource usersResource = KeycloakConfig.getUserResource();
        RolesResource rolesResource = KeycloakConfig.getRealmResource().roles();
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(keycloakUser.getFirstName());
        userRepresentation.setLastName(keycloakUser.getLastName());
        userRepresentation.setEmail(keycloakUser.getEmail());
        userRepresentation.setUsername(keycloakUser.getUsername());
        userRepresentation.setRealmRoles(Collections.singletonList("Candidat"));
        Map<String, List<String>> attributes = new HashMap<>();
        userRepresentation.setAttributes(attributes);
        attributes.put("cin", Collections.singletonList(keycloakUser.getCin()));
        userRepresentation.setAttributes(attributes);
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        Response response = usersResource.create(userRepresentation);
        String path = response.getLocation().getPath();
        String userId = path.substring(path.lastIndexOf("/") + 1);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(keycloakUser.getPassword());

        usersResource.get(userId).resetPassword(credentialRepresentation);

        RoleRepresentation role = rolesResource.get("Candidat").toRepresentation();
        usersResource.get(userId).roles().realmLevel().add(Collections.singletonList(role));




        return "User added.";
    }

    public UsersResource getInstance() {
        return KeycloakConfig.getUserResource();
    }


    public UserRepresentation getUser(String userId) {
        UserRepresentation user = KeycloakConfig.getRealmResource()
                .users().get(userId).toRepresentation();
        return user;

    }
    public UserRepresentation getUserwithName(String username) {
        UserRepresentation user = KeycloakConfig.getRealmResource()
                .users().get(username).toRepresentation();
        return user;

    }
    public List<KeycloakUserDto> getUsersWithRole() {
        RealmResource realmResource = KeycloakConfig.getRealmResource();
        RoleRepresentation roleRepresentation = getRoleRepresentation(realmResource, "Candidat");
        if (roleRepresentation == null) {
            return Collections.emptyList();
        }

        List<KeycloakUserDto> usersWithRole = new ArrayList<>();
        UsersResource usersResource = realmResource.users();

        List<UserRepresentation> allUsers = usersResource.list();

        for (UserRepresentation user : allUsers) {
            if (hasRole(usersResource, user, roleRepresentation)) {
                KeycloakUserDto keycloakUser = new KeycloakUserDto();
                keycloakUser.setEmail(user.getEmail());
                keycloakUser.setUsername(user.getUsername());
                keycloakUser.setFirstName(user.getFirstName());
                keycloakUser.setLastName(user.getLastName());

                List<String> cinList = user.getAttributes().get("cin");
                if (cinList != null && !cinList.isEmpty()) {
                    String cin = cinList.get(0); // Retrieve the first element
                    keycloakUser.setCin(cin);
                }
                usersWithRole.add(keycloakUser);
            }
        }

        return usersWithRole;
    }


    private RoleRepresentation getRoleRepresentation(RealmResource realmResource, String roleName) {
        List<RoleRepresentation> allRoles = realmResource.roles().list();
        for (RoleRepresentation role : allRoles) {
            if (role.getName().equals(roleName)) {
                return role;
            }
        }
        return null;
    }

    private boolean hasRole(UsersResource usersResource, UserRepresentation user, RoleRepresentation role) {
        List<RoleRepresentation> userRoles = usersResource.get(user.getId()).roles().realmLevel().listAll();
        return userRoles.stream().anyMatch(userRole -> userRole.getName().equals(role.getName()));
    }

    public UserRepresentation getUserByName(String username) {
        UserRepresentation user = KeycloakConfig.getRealmResource()
                .users().search(username).stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User not found"));
        return user;

    }
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserRepresentation> getUsers() {
        UsersResource usersResource = getInstance();
        List<UserRepresentation> user = usersResource.list();
        return user;

    }
    public void updateUserByAmin(String userId, KeycloakUser keycloakUser) {
        CredentialRepresentation credential = createPasswordCredentials(keycloakUser.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setFirstName(keycloakUser.getFirstName());
        user.setLastName(keycloakUser.getLastName());
        user.setEmail(keycloakUser.getEmail());
        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = getInstance();
        usersResource.get(userId).update(user);
    }

    public void updateUser(String userId, KeycloakUser keycloakUser) {
        CredentialRepresentation credential = createPasswordCredentials(keycloakUser.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(keycloakUser.getUsername());
        user.setFirstName(keycloakUser.getFirstName());
        user.setLastName(keycloakUser.getLastName());
        user.setEmail(keycloakUser.getEmail());
        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = getInstance();
        usersResource.get(userId).update(user);
    }


    public void deleteUser(String userId) {
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .remove();
    }
    public void deleteMyAccount(String userId) {
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .remove();
    }


    public void assignRealmRolesToUser(KeycloakUser keycloakUser, String userName) {
        UsersResource usersResource = KeycloakConfig.getUserResource();
        UserRepresentation user = usersResource.search(userName).get(0);
        usersResource = KeycloakConfig.getUserResource();
        user = usersResource.search(userName).get(0);
        RoleRepresentation role = KeycloakConfig.getRealmResource().roles().get(keycloakUser.getRole()).toRepresentation();
        RoleMappingResource roleMappingResource = usersResource.get(user.getId()).roles();
        roleMappingResource.realmLevel().add(Collections.singletonList(role));

        List<RoleRepresentation> assignedRoles = roleMappingResource.realmLevel().listEffective();
        boolean roleAssigned = assignedRoles.stream()
                .map(RoleRepresentation::getName)
                .anyMatch(keycloakUser.getRole()::equals);
        if (!roleAssigned) {
            throw new RuntimeException("Failed to assign role to user.");
        }

    }
    public void assignRoleToUser(String userId, String roleName) {
        RealmResource realmResource = KeycloakConfig.getRealmResource();
        UsersResource usersResource = realmResource.users();
        RolesResource rolesResource = realmResource.roles();

        RoleRepresentation role = rolesResource.get(roleName).toRepresentation();
        usersResource.get(userId).roles().realmLevel().add(Collections.singletonList(role));
    }

}
