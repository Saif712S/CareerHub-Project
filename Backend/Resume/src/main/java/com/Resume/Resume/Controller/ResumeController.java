package com.Resume.Resume.Controller;

import com.Resume.Resume.Entity.Resume;
import com.Resume.Resume.Repo.ResumeRepo;
import com.Resume.Resume.Services.ResumeService;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Resume")
@AllArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final ResumeRepo resumeRepo;
    @PostMapping("/addResume")
    public ResponseEntity createResume(@RequestBody Resume resume, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String username = context.getToken().getPreferredUsername();
        System.out.println(username);
        Resume addedResume = resumeService.addResume(resume, username);
        return new ResponseEntity<>(addedResume, HttpStatus.CREATED);
    }
    @PutMapping("/updateResume/{id}")
    public ResponseEntity updateResume(@PathVariable Long id, @RequestBody Resume updatedResume) {
        resumeService.updateResume(id, updatedResume);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Resume> getResumeById(@PathVariable Long id) {
        return resumeService.getResumeById(id);
    }
    @GetMapping("/getResumes")
    public ResponseEntity<List<Resume>> getAllResumes() {
        List<Resume> resume = resumeService.getResume();
        if (!resume.isEmpty()) {
            return ResponseEntity.ok(resume);
        } else {
            return ResponseEntity.notFound().build();
        }}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteResume(@PathVariable Long id) {
        Map<String, Boolean> response =resumeService.deleteResume(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
