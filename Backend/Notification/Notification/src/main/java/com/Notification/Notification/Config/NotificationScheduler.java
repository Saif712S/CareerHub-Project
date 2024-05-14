package com.Notification.Notification.Config;

import com.Notification.Notification.Services.NotificationService;
import com.Notification.Notification.Services.NotificationServiceImpl;
import com.Notification.Notification.UserMicroservice.Candidature;
import com.Notification.Notification.UserMicroservice.CandidatureFeignClient;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableScheduling
public class NotificationScheduler {
@Autowired
    private  CandidatureFeignClient candidatureFeignClient;
    private final NotificationService notificationService;

    public NotificationScheduler( NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @Scheduled(cron = "0 */3 * * * *")
    public void sendMailToAcceptedCandidature() {
        List<Candidature> candidatures = candidatureFeignClient.getCandidatureByStatus();
        for (Candidature candidature : candidatures) {
            String username = candidature.getUsername();
            String titre = candidature.getOffreEmploi().getTitre();
            String RhName = candidature.getOffreEmploi().getUsername();

            UsersResource usersResource = KeycloakConfig.getRealmResource().users();
            List<UserRepresentation> userRepresentations = usersResource.search(username);

            if (!userRepresentations.isEmpty()) {
                UserRepresentation user = userRepresentations.get(0);
                String email = user.getEmail();

                LocalDate currentDate = LocalDate.now();
                // Add three days to the current date
                LocalDate resultDate = currentDate.plusDays(3);

                StringBuilder message =new StringBuilder("Cher " + username + "\n" +
                        "Nous avons bien reçu votre candidature pour le sujet de " + titre + ".\n" +
                        "Nous avons le plaisir de vous inviter à un entretien qui se déroulera le " + resultDate + " dans nos locaux.\n" +
                        "Veuillez arriver quelques minutes en avance afin de faciliter le début de l'entretien.\n" +
                        "Lors de cet entretien, nous aurons l'occasion de discuter plus en détail de votre candidature, de vos compétences et de vos attentes.\n" +
                        "Cordialement,\n" + RhName) ;

                notificationService.sendNotificationToEmail(email, String.valueOf(message));
            }
        }
    }
}
