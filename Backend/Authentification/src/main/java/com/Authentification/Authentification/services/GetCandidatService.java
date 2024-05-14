package com.Authentification.Authentification.services;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Collections;
import java.util.List;

public class GetCandidatService {
    private Keycloak keycloak;

    public GetCandidatService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public List<UserRepresentation> getCandidatUsers() {
        RealmResource realmResource = keycloak.realm("CareerHub");
        UsersResource usersResource = realmResource.users();

        // Get the "Candidat" role in the realm
        RolesResource rolesResource = realmResource.roles();
        RoleResource candidatRoleResource = rolesResource.get("Candidat");
        RoleRepresentation candidatRole = candidatRoleResource.toRepresentation();
        System.out.println("Candidat Role ID: " + candidatRole.getId());

        // Get users with the "Candidat" role
        try {
            List<UserRepresentation> candidatUsers = usersResource.search(null, null, null, null, null, null);
            return candidatUsers;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
