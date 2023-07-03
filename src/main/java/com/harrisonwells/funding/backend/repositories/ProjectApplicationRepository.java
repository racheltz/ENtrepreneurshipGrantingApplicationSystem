package com.harrisonwells.funding.backend.repositories;

import com.harrisonwells.funding.backend.models.ProjectApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectApplicationRepository extends JpaRepository<ProjectApplication, Long> {
    List<ProjectApplication> findByEntrepreneur(String entrepreneur);

    @Query(value = "select * from projectapplication where ANNOUNCEMENT_ID = ?1 AND entrepreneur = ?2", nativeQuery = true)
    Optional<ProjectApplication> findByAnnouncementAndEntrepreneur(Long announcementId, String entrepreneur);
    @Query(value = "select * from projectapplication where announcement.investor = ?1", nativeQuery = true)
    List<ProjectApplication> findByInvestor(String investor);
}
