package com.harrisonwells.funding.backend.services;

import com.harrisonwells.funding.backend.models.ProjectApplication;
import com.harrisonwells.funding.backend.repositories.ProjectApplicationRepository;
import com.harrisonwells.funding.security.MyUserDetailsService;
import com.harrisonwells.funding.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectApplicationService implements CrudListener<ProjectApplication> {

    private final ProjectApplicationRepository projectApplicationRepository;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    public boolean isProjectAlreadyApplied(Long announcementId) {

        UserDetails userDetails = SecurityUtils.getAuthenticatedUser();
        if (userDetails != null) {
            Optional<ProjectApplication> projectApplication = projectApplicationRepository.findByAnnouncementAndEntrepreneur(announcementId, userDetails.getUsername());
            return projectApplication.isPresent();
        }

        return false;
    }

    public boolean isProjectAlreadyFunded(Long announcementId) {

        UserDetails userDetails = SecurityUtils.getAuthenticatedUser();
        if (userDetails != null) {
            Optional<ProjectApplication> projectApplication = projectApplicationRepository.findByAnnouncementAndEntrepreneur(announcementId, userDetails.getUsername());
            if (projectApplication.isPresent()) {
                if (projectApplication.get().getIsFunded() == null) {
                    return false;
                }
                return projectApplication.get().getIsFunded();
            }
        }

        return false;
    }

    @Override
    public Collection<ProjectApplication> findAll() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails != null) {
            Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

            boolean isInvestor = false;

            for (GrantedAuthority role : roles) {
                if (role.getAuthority().equals("ROLE_INVESTOR")) {
                    isInvestor = true;
                }
            }

            if (isInvestor){
                List<ProjectApplication> projectApplications = projectApplicationRepository.findAll();
                List<ProjectApplication> list = new ArrayList<>();
                for (ProjectApplication projectApplication : projectApplications) {
                    if (projectApplication.getAnnouncement().getInvestor().equals(userDetails.getUsername())){
                        list.add(projectApplication);
                    }
                }
                return list;
            }else {
                return projectApplicationRepository.findByEntrepreneur(userDetails.getUsername());
            }
        }
        return null;
    }

    @Override
    public ProjectApplication add(ProjectApplication projectApplication) {
        UserDetails userDetails = SecurityUtils.getAuthenticatedUser();
        if (userDetails != null) {
            projectApplication.setEntrepreneur(userDetails.getUsername());
            projectApplication.setIsFunded(false);
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
