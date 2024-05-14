package com.Notification.Notification.UserMicroservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Component
@FeignClient(name = "Candidature",url = "http://localhost:8083/candidature")
public interface CandidatureFeignClient {
    @GetMapping("/getCandidatureByStatus")
     List<Candidature> getCandidatureByStatus();

}
