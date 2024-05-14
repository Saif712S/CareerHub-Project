package com.Entreprise.Entreprise.Service;

import com.Entreprise.Entreprise.Entity.Entreprise;

import java.util.List;
import java.util.Optional;

public interface EntrepriseService {



    List<Entreprise> getEntreprise();


    Optional<Entreprise> getEntrepriseById(Long id);

    void deleteEntreprise(Long id);

    void updateEntreprise(Long id, Entreprise updatedEntreprise);

    Entreprise addEntreprise(Entreprise entreprise, String username);
}
