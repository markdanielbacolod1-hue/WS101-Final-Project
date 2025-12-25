package com.example.websystems.entity;

import jakarta.persistence.*;
import com.example.websystems.entity.User;


import java.time.LocalDate;

@Entity
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private Integer announcementId;

    private String title;
    private String details;

    @Column(name = "date_posted")
    private LocalDate datePosted;

    private String position;

    @Column(name = "attachment_url")
    private String attachmentUrl;

    @ManyToOne
    @JoinColumn(name = "posted_by")
    private User postedBy;
}
