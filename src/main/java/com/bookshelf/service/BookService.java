package com.bookshelf.service;

import com.bookshelf.model.Book;
import com.bookshelf.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
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

    public boolean existsById(long id){
        return bookRepository.existsById(id);
    }

    public void delete(long id){
        bookRepository.deleteById(id);
    }
}
