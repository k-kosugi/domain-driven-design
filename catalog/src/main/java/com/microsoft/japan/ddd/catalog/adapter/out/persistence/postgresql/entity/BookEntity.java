package com.microsoft.japan.ddd.catalog.adapter.out.persistence.postgresql.entity;

import java.io.Serializable;
import java.util.List;

import com.microsoft.japan.ddd.catalog.domain.model.Title;
import com.microsoft.japan.ddd.catalog.domain.model.isbn.ISBN13;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class BookEntity implements Serializable {

    @Id
    @Column(nullable = false, length = ISBN13.LENGTH_WITH_PREFIX_AND_SEPARATOR)
    private String isbn13;

    @Column(nullable = false, length = Title.MAX_LENGTH)
    private String title;

    @Column(nullable = false)
    private String contents;

    @OneToMany
    @JoinColumn(name = "isbn13", nullable = false)
    private List<AuthorEntity> authors;

    @OneToOne
    @JoinColumn(name = "publisher_id")
    private PublisherEntity publisher;

    @Column(name = "embedding", columnDefinition = "vector(1536)")
    private float[] embedding;

    public BookEntity() {
    }

    public BookEntity(String isbn13, String title, String contents,
                      List<AuthorEntity> authors, PublisherEntity publisher) {
        this.isbn13 = isbn13;
        this.title = title;
        this.contents = contents;
        this.authors = authors;
        this.publisher = publisher;
    }

    public String getIsbn13() {
        return this.isbn13;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContents() {
        return this.contents;
    }

    public List<AuthorEntity> getAuthors() {
        return this.authors;
    }

    public PublisherEntity getPublisher() {
        return this.publisher;
    }

    public float[] getEmbedding() {
        return this.embedding;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }

    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }

    public void setEmbedding(float[] embedding) {
        this.embedding = embedding;
    }

}
