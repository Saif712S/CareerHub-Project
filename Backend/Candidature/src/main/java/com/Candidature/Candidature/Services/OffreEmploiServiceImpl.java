package com.Candidature.Candidature.Services;

import com.Candidature.Candidature.Entity.OffreEmploi;
import com.Candidature.Candidature.Repo.OffreEmploiRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
@EnableScheduling
public class OffreEmploiServiceImpl implements OffreEmploiService{
    @Autowired
    OffreEmploiRepo offreEmploiRepo;

    @Override
    public OffreEmploi addOffreEmploi(OffreEmploi offreEmploi,String username) {
        offreEmploi.setUsername(username);
        return offreEmploiRepo.save(offreEmploi);
    }






    @Override
    public OffreEmploi updateOffreEmploi(OffreEmploi offreEmploi, Long id) {
        OffreEmploi old = offreEmploiRepo.findById(id).orElse(null);
        old.setTitre(offreEmploi.getTitre());
        old.setTypeOffre(offreEmploi.getTypeOffre());
        old.setPostesVacants(offreEmploi.getPostesVacants());
        old.setGenre(offreEmploi.getGenre());
        old.setLangues(offreEmploi.getLangues());
        old.setSalaire(offreEmploi.getSalaire());
        old.setExigencesEmploi(offreEmploi.getExigencesEmploi());
        old.setDateFin(offreEmploi.getDateFin());
        old.setDescription(offreEmploi.getDescription());
        return offreEmploiRepo.save(old);
    }
    @Override
    public List<OffreEmploi> getOffreEmploi() {
        return offreEmploiRepo.findAll();
    }

    @Override
    public Map<String, Boolean> deleteOffreEmploi(Long id) {
        offreEmploiRepo.deleteById(id);
        Map<String,Boolean> res = new HashMap<>();
        res.put("deleted",Boolean.TRUE);
        return res;
    }







}

