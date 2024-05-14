package com.Entreprise.Entreprise.Service;

import com.Entreprise.Entreprise.Entity.Entreprise;
import com.Entreprise.Entreprise.Repo.EntrepriseRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@EnableScheduling
public class EntrepriseServiceImpl implements EntrepriseService{
    @Autowired
    EntrepriseRepo entrepriseRepo;


    @Override
    public List<Entreprise> getEntreprise() {
        return entrepriseRepo.findAll();
    }

    @Override
    public Optional<Entreprise> getEntrepriseById(Long id) {
        return entrepriseRepo.findById(id);
    }
    @Override
    public void deleteEntreprise(Long id) {
        entrepriseRepo.deleteById(id);
    }

    public void updateEntreprise(Long id, Entreprise updatedEntreprise) {
        Optional<Entreprise> existingEntrepriseOptional = entrepriseRepo.findById(id);
        if (existingEntrepriseOptional.isPresent()) {
            Entreprise existingEntreprise = existingEntrepriseOptional.get();
            existingEntreprise.setNom(updatedEntreprise.getNom());
            existingEntreprise.setDescription(updatedEntreprise.getDescription());
            existingEntreprise.setLogo(updatedEntreprise.getLogo());
            existingEntreprise.setSecteur(updatedEntreprise.getSecteur());
            entrepriseRepo.save(existingEntreprise);
        } else {
            System.out.println("Entreprise avec l'ID " + id + " n'a pas été trouvée");
        }
    }

    @Override
    public Entreprise addEntreprise(Entreprise entreprise, String username) {
        entreprise.setUsername(username);
        return entrepriseRepo.save(entreprise);
    }
    }






