package com.Entreprise.Entreprise.Repo;

import com.Entreprise.Entreprise.Entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntrepriseRepo extends JpaRepository<Entreprise,Long> {



}
