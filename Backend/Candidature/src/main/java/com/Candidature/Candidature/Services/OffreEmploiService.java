package com.Candidature.Candidature.Services;

import com.Candidature.Candidature.Entity.OffreEmploi;

import java.util.List;
import java.util.Map;

public interface OffreEmploiService {

    OffreEmploi addOffreEmploi(OffreEmploi offreEmploi, String username);

    OffreEmploi updateOffreEmploi(OffreEmploi offreEmploi, Long id);




    List<OffreEmploi> getOffreEmploi();


    Map<String, Boolean> deleteOffreEmploi(Long id);
}
