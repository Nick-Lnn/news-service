package com.dharbor.newsservice.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

/**
 * @author Nicolas
 */
@Data
@Entity
@Table(name = Constants.AttachementTable.NAME)
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private String fieldId;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false)
    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bulletin_id")
    private Bulletin bulletin;

    @PrePersist
    protected void onCreate() {
        createdDate = Instant.now();
        isDeleted = false;
    }
}
