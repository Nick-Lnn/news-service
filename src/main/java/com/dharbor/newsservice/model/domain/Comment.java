package com.dharbor.newsservice.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

/**
 * @author Nicolas
 */
@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private Long senderUserId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer repliesCounter;

    @Column(nullable = false)
    private Instant createdDate;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bulletin_id")
    private Bulletin bulletin;

    @PrePersist
    protected void onCreate() {
        createdDate = Instant.now();
        isDeleted = false;
        repliesCounter = 0;
    }
}