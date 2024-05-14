package com.Resume.Resume.Services;

import com.Resume.Resume.Entity.Resume;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ResumeService {
    Resume addResume(Resume newResume, String username);

    void updateResume(Long id, Resume updatedResume);

    ResponseEntity<Resume> getResumeById(Long id);




    List<Resume> getResume();

    Map<String, Boolean> deleteResume(Long id);
}
