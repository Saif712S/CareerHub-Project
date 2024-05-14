package com.Entreprise.Entreprise.Controller;

import com.Entreprise.Entreprise.Entity.Entreprise;
import com.Entreprise.Entreprise.Service.EntrepriseService;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Entreprise")
public class EntrepriseController {
    private final EntrepriseService entrepriseService ;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }
    @PostMapping("/add")
    public ResponseEntity<Entreprise> addEntreprise(@RequestBody Entreprise entreprise, KeycloakAuthenticationToken auth) {
        // Votre logique pour obtenir le nom d'utilisateur à partir du token Keycloak
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String username = context.getToken().getPreferredUsername();
        System.out.println(username);

        // Votre logique pour ajouter une entreprise
        Entreprise newEntreprise = entrepriseService.addEntreprise(entreprise, username);

        // Retourner la réponse avec le nouveau objet entreprise créé
        return new ResponseEntity<>(newEntreprise, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Entreprise>> getEntreprise() {
        List<Entreprise> entrepriseList = entrepriseService.getEntreprise();
        return new ResponseEntity<>(entrepriseList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable("id") Long id) {
        Optional<Entreprise> entreprise = entrepriseService.getEntrepriseById(id);
        return entreprise.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntreprise(@PathVariable("id") Long id) {
        entrepriseService.deleteEntreprise(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Entreprise> updateEntreprise(@PathVariable("id") Long id, @RequestBody Entreprise updatedEntreprise) {
        entrepriseService.updateEntreprise(id, updatedEntreprise);
        // Récupérer l'entreprise mise à jour
        Optional<Entreprise> updatedEntrepriseOptional = entrepriseService.getEntrepriseById(id);
        if (updatedEntrepriseOptional.isPresent()) {
            Entreprise updatedEntity = updatedEntrepriseOptional.get();
            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}


