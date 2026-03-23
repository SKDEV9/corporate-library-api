package com.SKDEV9.corporate_library_api.service;

import com.SKDEV9.corporate_library_api.dto.CreateBookDTO;
import com.SKDEV9.corporate_library_api.model.Book;
import com.SKDEV9.corporate_library_api.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book createBook(CreateBookDTO dto) {

        Book newBook = new Book();
        newBook.setAuthor(dto.author());
        newBook.setTitle(dto.title());
        newBook.setIsbn(dto.isbn());


        newBook.setAvailable(true);

        return repository.save(newBook);
    }

    public List<Book> listAllBooks() {
        return repository.findAll();
    }

}
