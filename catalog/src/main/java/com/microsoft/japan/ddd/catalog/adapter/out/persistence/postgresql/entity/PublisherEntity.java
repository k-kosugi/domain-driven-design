package com.microsoft.japan.ddd.catalog.adapter.out.persistence.postgresql.entity;

import java.io.Serializable;

import com.microsoft.japan.ddd.catalog.domain.model.PublisherId;
import com.microsoft.japan.ddd.catalog.domain.model.PublisherName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "publisher")
public class PublisherEntity implements Serializable{

    @Id
    @Column(nullable = false, length = PublisherId.MAX_LENGTH)
    private String id;

    @Column(nullable = false, length = PublisherName.MAX_LENGTH)
    private String name;

    public PublisherEntity() {
    }

    public PublisherEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
