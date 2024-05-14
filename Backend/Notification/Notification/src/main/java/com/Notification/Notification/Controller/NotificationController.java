package com.Notification.Notification.Controller;

import com.Notification.Notification.Entity.Notification;
import com.Notification.Notification.Repo.NotificationRepo;
import com.Notification.Notification.Services.NotificationService;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Notification")
@AllArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationRepo notificationRepo;


}
