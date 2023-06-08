package com.harrisonwells.funding.backend.repositories;

import com.harrisonwells.funding.backend.models.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByInvestor(String investor);
}
