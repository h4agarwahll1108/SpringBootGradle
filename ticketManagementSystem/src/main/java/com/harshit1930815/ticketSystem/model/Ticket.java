package com.harshit1930815.ticketSystem.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TICKET")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name ="description")
    private String description;

    @Column(name ="created_date")
    private LocalDateTime createdDate;

    @Column(name ="expiry")
    private LocalDateTime expiry;


    public Ticket(Long id, String title, String description, LocalDateTime createdDate, LocalDateTime expiry) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.expiry = expiry;
    }

    public Ticket() {
	super();
}

	public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getExpiry() {
        return expiry;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setExpiry(LocalDateTime expiry) {
        this.expiry = expiry;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", expiry=" + expiry +
                //", createdBy=" + createdBy +
                '}';
    }
}
