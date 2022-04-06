package com.br.example.prj.biblioteca.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.example.prj.biblioteca.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	Page<Book> findAll(Pageable pageable);

	//Page<Book> findByPublished(boolean published, Pageable pageable);

	Page<Book> findByTitleContaining(String title, Pageable pageable);

}
