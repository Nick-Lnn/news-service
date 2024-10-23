package com.dharbor.newsservice.model.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * @author Nicolas
 */
@Data
@Entity
@Table(name = "bulletins")
public class Bulletin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private Long senderUserId;

    @Column(nullable = false)
    private String senderUsername;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private Instant createdDate;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false)
    private Integer commentsCounter;

    @OneToMany(mappedBy = "bulletin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "bulletin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments;

    @PrePersist
    protected void onCreate() {
        createdDate = Instant.now();
        isDeleted = false;
        commentsCounter = 0;
    }
}
