package com.bookshelf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
@Entity
@Table(name="book")
public class Book {
    public Book(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", unique=true, nullable=false)
    private String title;

    @Column(name="subtitle")
    private String subtitle;

    @Column(name="author")
    private String author;

    @Column(name="publisher")
    private String publisher;

    @Column(name="pages")
    private int pages;

    @Column(name="status")
    private int status;

    @Column(name="rating")
    private Float rating;

    @Column(name="book_cover_url")
    private String bookCover;
}