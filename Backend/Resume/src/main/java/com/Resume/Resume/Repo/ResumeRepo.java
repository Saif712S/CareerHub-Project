package com.Resume.Resume.Repo;

import com.Resume.Resume.Entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepo extends JpaRepository<Resume,Long> {
}
