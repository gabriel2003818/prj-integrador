package com.br.example.prj.biblioteca.services.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.example.prj.biblioteca.models.Book;
import com.br.example.prj.biblioteca.repositories.BookRepository;
import com.br.example.prj.biblioteca.services.BookService;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Page<Book> findAll(Pageable pageable) {

		return bookRepository.findAll(pageable);
	}

	@Override
	public Page<Book> findByTitleContaining(String title, Pageable pageable) {
		return bookRepository.findByTitleContaining(title, pageable);
	}

	@Override
	public Book save(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
	}

	@Override
	public Optional<Book> findById(Long bookId) {
		return bookRepository.findById(bookId);
	}

	@Override
	public void deleteBookById(Long bookId) {
		bookRepository.deleteById(bookId);
	}

}
