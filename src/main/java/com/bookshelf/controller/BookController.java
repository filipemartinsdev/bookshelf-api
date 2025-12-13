package com.bookshelf.controller;

import com.bookshelf.model.Book;
import com.bookshelf.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
//    @Autowired
    private BookService bookService;

    public BookController(BookService bookshelfService){
        this.bookService = bookshelfService;
    }

    @GetMapping("/v1/books")
    public ResponseEntity<List<Book>> bookGet(@RequestParam(name="query", defaultValue="null") String query){
        List<Book> responseList;

        if (query.equals("null")){
            responseList = bookService.findAll();
        }
        else {
            responseList = bookService.searchByTitle(query);
        }
        if (responseList.isEmpty()){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        else {
            return ResponseEntity
                    .status(200)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseList);
        }
    }

    @PostMapping("/v1/books")
    public ResponseEntity<Book> bookPost(@RequestBody Book book){
        Book responseBook = bookService.save(book);
        if (responseBook==null){
            return ResponseEntity
                    .status(400)
                    .build();
        }

        else {
            return ResponseEntity
                    .status(201)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseBook);
        }
    }

    @DeleteMapping("/v1/books")
    public ResponseEntity<Object> bookDelete(@RequestBody Book book){
        if (bookService.existsById(book.getId())){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        else{
            bookService.delete(book.getId());
            return ResponseEntity
                    .status(203)
                    .build();
        }
    }
}
