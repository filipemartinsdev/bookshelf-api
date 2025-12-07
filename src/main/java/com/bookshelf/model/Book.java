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

    private String title;
    private String subtitle;
    private String author;
    private String publisher;
    private int pages;
    private int status;
    private float rating;
    private byte[] bookCover;
}