package com.br.example.prj.biblioteca.services;

import com.br.example.prj.biblioteca.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BookService {
    //implementar crud

    Page<Book> findAll(Pageable pageable);

    Page<Book> findByTitleContaining(String title, Pageable pageable);
    
    Book save(Book book);

    Optional<Book> findById(Long bookId);

    void deleteBookById(Long bookId);

}
