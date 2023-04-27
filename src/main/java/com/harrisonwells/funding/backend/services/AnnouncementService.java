package com.harrisonwells.funding.backend.services;

import com.harrisonwells.funding.backend.models.Announcement;
import com.harrisonwells.funding.backend.repositories.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AnnouncementService implements CrudListener<Announcement> {

    private final AnnouncementRepository announcementRepository;

    @Override
    public Collection<Announcement> findAll() {
        return announcementRepository.findAll();
    }

    @Override
    public Announcement add(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    @Override
    public Announcement update(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    @Override
    public void delete(Announcement announcement) {
        announcementRepository.delete(announcement);
    }
}
