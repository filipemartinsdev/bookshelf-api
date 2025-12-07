package com.bookshelf.repository;

import com.bookshelf.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>{
    public List<Book> findAllByTitleIgnoreCaseContaining(String title);
}