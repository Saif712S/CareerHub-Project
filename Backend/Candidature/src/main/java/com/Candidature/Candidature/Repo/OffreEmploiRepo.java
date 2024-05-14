package com.Candidature.Candidature.Repo;

import com.Candidature.Candidature.Entity.Candidature;
import com.Candidature.Candidature.Entity.OffreEmploi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OffreEmploiRepo extends JpaRepository<OffreEmploi,Long> {



    Set<OffreEmploi> getOffreEmploiByOffreId(Long offreId);
}
