package com.microsoft.japan.ddd.catalog.adapter.out.persistence.postgresql.entity;

import java.io.Serializable;

import com.microsoft.japan.ddd.catalog.domain.model.AuthorId;
import com.microsoft.japan.ddd.catalog.domain.model.AuthorName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "author")
public class AuthorEntity implements Serializable{

    @Id
    @Column(nullable = false, length = AuthorId.MAX_LENGTH)
    private String id;

    @Column(nullable = false, length = AuthorName.MAX_LENGTH)
    private String firstName;

    @Column(length = AuthorName.MAX_LENGTH)
    private String middleName;

    @Column(nullable = false, length = AuthorName.MAX_LENGTH)
    private String lastName;

    public AuthorEntity() {
    }

    public AuthorEntity(String id, String firstName, String middleName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
