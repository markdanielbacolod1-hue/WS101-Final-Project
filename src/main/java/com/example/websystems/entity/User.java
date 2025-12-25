package com.example.websystems.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    private String email;
    private String password;
    private String role;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "language_section")
    private String languageSection;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getLanguageSection() {
        return languageSection;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setLanguageSection(String languageSection) {
        this.languageSection = languageSection;
    }
}
