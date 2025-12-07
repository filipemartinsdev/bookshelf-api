package com.bookshelf.controller;

import com.bookshelf.model.Book;
import com.bookshelf.service.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookshelfController {
//    @Autowired
    private BookshelfService bookshelfService;

    public BookshelfController(BookshelfService bookshelfService){
        this.bookshelfService = bookshelfService;
    }

    @GetMapping("/v1/books")
    public ResponseEntity<List<Book>> bookGet(@RequestParam(name="query", defaultValue="null") String query){
        List<Book> responseList;

        if (query.equals("null")){
            responseList = bookshelfService.findAll();
        }
        else {
            responseList = bookshelfService.searchByTitle(query);
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
        Book responseBook = bookshelfService.save(book);
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
}
