package com.Resume.Resume.Services;


import com.Resume.Resume.Entity.Resume;
import com.Resume.Resume.Repo.ResumeRepo;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@EnableScheduling
public class ResumeServiceImpl implements ResumeService{
    @Autowired
    ResumeRepo resumeRepo ;

    @Override
    public Resume addResume(Resume newResume, String username) {

        newResume.setUsername(username);
        newResume.setContent(newResume.getContent());
        newResume.setLettre(newResume.getLettre());
        return resumeRepo.save(newResume);
    }
    public void updateResume(Long id, Resume updatedResume) {
        Optional<Resume> existingResumeOptional = resumeRepo.findById(id);
        if (existingResumeOptional.isPresent()) {
            Resume existingResume = existingResumeOptional.get();
            existingResume.setContent(updatedResume.getContent());
            existingResume.setLettre(updatedResume.getLettre());
           resumeRepo.save(existingResume);
        } else {
            throw new NotFoundException("Resume with ID " + id + " was not found");
        }
    }
    public ResponseEntity<Resume> getResumeById(Long id) {
        Resume getResume = resumeRepo.findById(id).get();
        return new ResponseEntity<>(getResume, HttpStatus.OK);
    }



    @Override
    public List<Resume> getResume() {
        return resumeRepo.findAll();
    }

    @Override
    public Map<String, Boolean> deleteResume(Long id) {
        resumeRepo.deleteById(id);
        Map<String,Boolean> res = new HashMap<>();
        res.put("deleted",Boolean.TRUE);
        return res;
    }
}
