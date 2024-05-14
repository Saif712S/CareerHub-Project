package com.Notification.Notification.UserMicroservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long candidatureId;
    private String username;
    private Date dateSoumission;
    private OffreEmploi offreEmploi;


}
