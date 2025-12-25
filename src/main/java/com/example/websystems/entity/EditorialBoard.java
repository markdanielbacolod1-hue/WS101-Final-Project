package com.example.websystems.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "editorial_board")
public class EditorialBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    private String position;

    @Column(name = "language_section")
    private String languageSection;

    private String contact;

    public Integer getMemberId() { return memberId; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getLastName() { return lastName; }
    public String getPosition() { return position; }
    public String getLanguageSection() { return languageSection; }
    public String getContact() { return contact; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setLanguageSection(String languageSection) {
        this.languageSection = languageSection;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
