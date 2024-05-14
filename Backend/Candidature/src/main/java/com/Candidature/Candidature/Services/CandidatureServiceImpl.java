package com.Candidature.Candidature.Services;

import com.Candidature.Candidature.Entity.Candidature;
import com.Candidature.Candidature.Entity.OffreEmploi;
import com.Candidature.Candidature.Entity.Status;
import com.Candidature.Candidature.Repo.CandidatureRepo;
import com.Candidature.Candidature.Repo.OffreEmploiRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
@AllArgsConstructor
@Slf4j
@EnableScheduling
public class CandidatureServiceImpl implements CandidatureService{
    @Autowired
    CandidatureRepo candidatureRepo;
    @Autowired
    OffreEmploiRepo offreEmploiRepo;
@Override
    public Candidature addCandidature(Candidature candidature,String username, Long offreId) {
        // Retrieve the OffreEmploi by its ID
        OffreEmploi offre = offreEmploiRepo.findById(offreId)
                .orElseThrow(() -> new IllegalArgumentException("OffreEmploi not found with ID: " + offreId));
System.out.println(offre);
        // Create a new Candidature
        candidature.setUsername(username);
        candidature.setDateSoumission(new Date());
        candidature.setStatus(candidature.getStatus()); // Assuming a default status


        candidature.setOffreEmploi(offre);
        return candidatureRepo.save(candidature);
    }





    public void updateCandidature(Long id, Candidature updatedCandidature) {
    Optional<Candidature> existingCandidatureOptional = candidatureRepo.findById(id);
    if (existingCandidatureOptional.isPresent()) {
        Candidature existingCandidature = existingCandidatureOptional.get();
        existingCandidature.setStatus(updatedCandidature.getStatus());
        candidatureRepo.save(existingCandidature);
    } else {
        System.out.println("Candidature avec l'ID " + id + " n'a pas été trouvée");
    }
}



@Override
    public Candidature getCandidatureById(Long id) {
        Candidature candidature=candidatureRepo.findById(id).orElse(null);
        return candidature;


    }

    @Override
    public List<Candidature> getCandidature() {
        return candidatureRepo.findAll();
    }



    @Override
    public void deleteCandidature (Long id) {
        candidatureRepo.deleteById(id);
    }


    @Override
    public List<Candidature> getCandidatureByStatus() {
        List<Candidature> candidatures=candidatureRepo.findByStatus(Status.Accepte);
        return candidatures;}







}
