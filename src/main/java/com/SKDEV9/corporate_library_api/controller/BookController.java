package com.SKDEV9.corporate_library_api.controller;

import com.SKDEV9.corporate_library_api.model.Book;
import com.SKDEV9.corporate_library_api.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.SKDEV9.corporate_library_api.dto.CreateBookDTO;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> createBook(@Valid @RequestBody CreateBookDTO dto) {

        Book savedBook = service.createBook(dto);

        // Return to the client a response HTTP 201 (CREATED) and the book is saved in the body of the response
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        List<Book> books = service.listAllBooks();

        return ResponseEntity.ok(books);
    }

}
