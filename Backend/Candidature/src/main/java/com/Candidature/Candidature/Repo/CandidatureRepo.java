package com.Candidature.Candidature.Repo;

import com.Candidature.Candidature.Entity.Candidature;
import com.Candidature.Candidature.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidatureRepo extends JpaRepository<Candidature,Long> {


    List<Candidature> findByStatus(Status status);
}
