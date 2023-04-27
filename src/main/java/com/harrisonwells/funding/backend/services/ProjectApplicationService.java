package com.harrisonwells.funding.backend.services;

import com.harrisonwells.funding.backend.models.ProjectApplication;
import com.harrisonwells.funding.backend.repositories.ProjectApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ProjectApplicationService implements CrudListener<ProjectApplication> {

    private final ProjectApplicationRepository projectApplicationRepository;

    @Override
    public Collection<ProjectApplication> findAll() {
        return projectApplicationRepository.findAll();
    }

    @Override
    public ProjectApplication add(ProjectApplication projectApplication) {
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
