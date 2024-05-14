package com.Candidature.Candidature.Services;

import com.Candidature.Candidature.Entity.Candidature;
import com.Candidature.Candidature.Entity.Status;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CandidatureService {


    List<Candidature> getCandidature();



 void deleteCandidature(Long id);



    Candidature getCandidatureById(Long id);



    Candidature addCandidature(Candidature candidature, String username, Long offreId);

    void updateCandidature(Long id, Candidature updatedCandidature);


    List<Candidature> getCandidatureByStatus();
}
