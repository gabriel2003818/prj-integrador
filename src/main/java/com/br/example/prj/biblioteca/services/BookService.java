package com.br.example.prj.biblioteca.services;

import com.br.example.prj.biblioteca.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BookService {
    //implementar crud

    Book save(Book book);

    //send and exception
    Optional<Book> findById(Long bookId);

    List<Book> findAllByTitle(String title,Pageable pageable);

    List<Book> findAllBooks();

    Page<Book> findAll(Pageable pageable);

    void deleteBookById(Long bookId);




}
