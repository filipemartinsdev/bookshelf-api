package com.bookshelf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="reading_status")
public enum ReadingStatus {
    UNREAD(0), READING(1), READ(2);

    @Id int id;
    private ReadingStatus(int id){
        this.id = id;
    }
}
