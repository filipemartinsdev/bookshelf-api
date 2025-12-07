package com.bookshelf.service;

import com.bookshelf.model.Book;
import com.bookshelf.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookshelfService {
    private BookRepository bookRepository;

    public BookshelfService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public List<Book> searchByTitle(String title){
        return bookRepository.findAllByTitleIgnoreCaseContaining(title);
    }

    public Book save(Book book){
//        if(bookRepository.existsById(book.getId())){
//            return null;
//        }
        return bookRepository.save(book);
    }

}
