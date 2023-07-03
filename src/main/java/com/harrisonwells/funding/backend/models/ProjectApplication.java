package com.harrisonwells.funding.backend.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ProjectApplication {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Announcement announcement;
    private String description;
    private String entrepreneur;
    private Boolean isFunded;
    @CreationTimestamp
    private Timestamp published;
}
