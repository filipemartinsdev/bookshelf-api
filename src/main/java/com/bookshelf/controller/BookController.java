package com.bookshelf.controller;

import com.bookshelf.model.Book;
import com.bookshelf.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    private BookService bookService;

    public BookController(BookService bookshelfService){
        this.bookService = bookshelfService;
    }

    @GetMapping("/")
    public ResponseEntity<String> bookHome(){
        return ResponseEntity
                .status(200)
                .body("HOME - BOOKSHELF API");
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

    @GetMapping("/v1/books/{id}")
    public ResponseEntity<Book> bookGet(@PathVariable Long id){
        Book responseBook;
        try {
            responseBook = bookService.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        }  catch (Exception e){
            return ResponseEntity
                    .status(400)
                    .build();
        }

        return ResponseEntity
                .status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseBook);
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

    @PatchMapping("/v1/books/{id}")
    public ResponseEntity<Book> bookPatch(@PathVariable Long id, @RequestBody Map<String, Object> bookMap){
        Book book = bookService.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book not found")
        );
        try {
            bookMap.forEach((key, value) -> {
                        switch (key) {
                            case "title" -> book.setTitle((String) value);
                            case "subtitle" -> book.setSubtitle((String) value);
                            case "author" -> book.setAuthor((String) value);
                            case "publisher" -> book.setPublisher((String) value);
                            case "pages" -> book.setPages((Integer) value);
                            case "status" -> book.setStatus((Integer) value);
                            case "rating" -> book.setRating((Float) value);
                            case "bookCover" -> book.setBookCover((String) value);
                            default -> throw new RuntimeException("Invalid attribute");
                        }
                    });
        } catch (Exception e){
            return ResponseEntity
                    .status(400)
                    .build();
        }

        Book bookSaved = bookService.save(book);
        return ResponseEntity
                .status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookSaved);
    }
}
