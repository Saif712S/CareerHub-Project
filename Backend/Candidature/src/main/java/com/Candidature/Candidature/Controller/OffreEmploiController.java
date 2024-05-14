package com.Candidature.Candidature.Controller;

import com.Candidature.Candidature.Entity.OffreEmploi;
import com.Candidature.Candidature.Services.OffreEmploiService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/offre-emploi")
public class OffreEmploiController {

    private final OffreEmploiService offreEmploiService;

    @Autowired
    public OffreEmploiController(OffreEmploiService offreEmploiService) {
        this.offreEmploiService = offreEmploiService;
    }

    @PostMapping("/add")
    public ResponseEntity<OffreEmploi> addOffreEmploi(@RequestBody OffreEmploi offreEmploi, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String username = context.getToken().getPreferredUsername();
        System.out.println(username);
        OffreEmploi newOffreEmploi = offreEmploiService.addOffreEmploi(offreEmploi,username);
        return new ResponseEntity<>(newOffreEmploi, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<OffreEmploi> updateOffreEmploi(@RequestBody OffreEmploi offreEmploi, @PathVariable Long id) {
        OffreEmploi updatedOffreEmploi = offreEmploiService.updateOffreEmploi(offreEmploi, id);
        return new ResponseEntity<>(updatedOffreEmploi, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<OffreEmploi>> getOffreEmploi() {
        List<OffreEmploi> offreEmploiList = offreEmploiService.getOffreEmploi();
        return new ResponseEntity<>(offreEmploiList, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOffreEmploi(@PathVariable Long id) {
        Map<String, Boolean> response = offreEmploiService.deleteOffreEmploi(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}