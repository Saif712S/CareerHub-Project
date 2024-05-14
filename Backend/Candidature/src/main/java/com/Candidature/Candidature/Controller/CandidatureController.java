package com.Candidature.Candidature.Controller;

import com.Candidature.Candidature.Entity.Candidature;
import com.Candidature.Candidature.Entity.Status;
import com.Candidature.Candidature.Repo.CandidatureRepo;
import com.Candidature.Candidature.Services.CandidatureService;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidature")
@AllArgsConstructor
public class CandidatureController {
    private final CandidatureService candidatureService;
    private final CandidatureRepo candidatureRepo;

    @PostMapping("/add/{offreId}")
    public ResponseEntity<Candidature> createCandidature(@RequestBody Candidature candidature, KeycloakAuthenticationToken auth, @PathVariable("offreId") Long offreId) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String username = context.getToken().getPreferredUsername();
        System.out.println(username);
        Candidature createdCandidature = candidatureService.addCandidature(candidature, username, offreId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCandidature);
    }


    @GetMapping("/{id}")
    public Candidature getCandidatureById(@PathVariable Long id) {
        return candidatureService.getCandidatureById(id);
    }
    @GetMapping("/getCandidatures")
    public ResponseEntity<List<Candidature>> getAllCandidatures( KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String username = context.getToken().getPreferredUsername();
        System.out.println(username);
        List<Candidature> candidatures = candidatureService.getCandidature();
        if (!candidatures.isEmpty()) {
            return ResponseEntity.ok(candidatures);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCandidature(@PathVariable("id") Long id) {
       candidatureService.deleteCandidature(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public HttpStatus updateCandidature(@PathVariable("id") Long id, @RequestBody Candidature updatedCandidature) {
        // Récupérer la candidature mise à jour
        candidatureService.updateCandidature(id, updatedCandidature);

            return HttpStatus.OK;
        }
    @GetMapping("/getCandidatureByStatus")
    public List<Candidature>getCandidatureByStatus() {
        return candidatureService.getCandidatureByStatus();
    }
    }






