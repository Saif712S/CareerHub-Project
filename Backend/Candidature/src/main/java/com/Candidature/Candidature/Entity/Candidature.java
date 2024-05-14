package com.Candidature.Candidature.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Candidature")

public class Candidature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long candidatureId;
    private String username;
    private Date dateSoumission;
    @Enumerated(EnumType.STRING)

    private Status status;
    @ManyToOne
private OffreEmploi offreEmploi;



}
