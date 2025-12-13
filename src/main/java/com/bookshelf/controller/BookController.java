package com.bookshelf.controller;

import com.bookshelf.model.Book;
import com.bookshelf.service.BookService;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<Book> responseList = new ArrayList<>();

        try{
            if (query.equals("null")){
                responseList = bookService.findAll();
            }
            else {
                responseList = bookService.searchByTitle(query);
            }
        }
        catch (Exception e){
            return ResponseEntity
                    .status(400)
                    .build();
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
        try{
            Book responseBook = bookService.save(book);
            return ResponseEntity
                    .status(201)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseBook);
        } catch (Exception e){
            return ResponseEntity
                    .status(400)
                    .build();
        }
    }

    @DeleteMapping("/v1/books/{id}")
    public ResponseEntity<Void> bookDelete(@PathVariable Integer id){
        try {
            if (!bookService.existsById(id)){
                return ResponseEntity
                        .status(404)
                        .build();
            }

            bookService.delete(id);
            return ResponseEntity
                    .status(204)
                    .build();
        }
        catch (Exception e){
            return ResponseEntity
                    .status(400)
                    .build();
        }
    }
}
