package com.harrisonwells.funding.backend.services;

import com.harrisonwells.funding.backend.models.ProjectApplication;
import com.harrisonwells.funding.backend.repositories.ProjectApplicationRepository;
import com.harrisonwells.funding.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectApplicationService implements CrudListener<ProjectApplication> {

    private final ProjectApplicationRepository projectApplicationRepository;


    public boolean isProjectAlreadyApplied(Long announcementId) {

        UserDetails userDetails = SecurityUtils.getAuthenticatedUser();
        if (userDetails != null) {
            Optional<ProjectApplication> projectApplication = projectApplicationRepository.findByAnnouncementAndEntrepreneur(announcementId, userDetails.getUsername());
            return projectApplication.isPresent();
        }

        return false;
    }

    @Override
    public Collection<ProjectApplication> findAll() {
        UserDetails userDetails = SecurityUtils.getAuthenticatedUser();
        if (userDetails != null) {
            return projectApplicationRepository.findByEntrepreneur(userDetails.getUsername());
        }
        return null;
    }

    @Override
    public ProjectApplication add(ProjectApplication projectApplication) {
        UserDetails userDetails = SecurityUtils.getAuthenticatedUser();
        if (userDetails != null) {
            projectApplication.setEntrepreneur(userDetails.getUsername());
        }
        return projectApplicationRepository.save(projectApplication);
    }

    @Override
    public ProjectApplication update(ProjectApplication projectApplication) {
        return projectApplicationRepository.save(projectApplication);
    }

    @Override
    public void delete(ProjectApplication projectApplication) {
        projectApplicationRepository.delete(projectApplication);
    }
}
