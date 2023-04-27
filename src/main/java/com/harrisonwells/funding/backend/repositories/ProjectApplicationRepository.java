package com.harrisonwells.funding.backend.repositories;

import com.harrisonwells.funding.backend.models.Announcement;
import com.harrisonwells.funding.backend.models.ProjectApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectApplicationRepository extends JpaRepository<ProjectApplication, Long> {
}
