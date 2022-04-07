package com.br.example.prj.biblioteca.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.example.prj.biblioteca.DTO.request.RequestCreateBookDto;
import com.br.example.prj.biblioteca.DTO.response.ResponseCreateBookDto;
import com.br.example.prj.biblioteca.models.Book;
import com.br.example.prj.biblioteca.services.impl.BookServiceImpl;

@RestController
@RequestMapping("/livros")
public class LivroController {

	private final BookServiceImpl bookServiceImpl;

	public LivroController(BookServiceImpl bookServiceImpl) {
		this.bookServiceImpl = bookServiceImpl;
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllBooks(@RequestParam(required = false) String title,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size) {

		try {
			List<Book> books = new ArrayList<Book>();
			Pageable paging = PageRequest.of(page, size);

			Page<Book> pageBooks;
			if (title == null)
				pageBooks = bookServiceImpl.findAll(paging);
			else
				pageBooks = bookServiceImpl.findByTitleContaining(title, paging);

			books = pageBooks.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("books", books);
			response.put("currentPage", pageBooks.getNumber());
			response.put("totalItems", pageBooks.getTotalElements());
			response.put("totalPages", pageBooks.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/add")
	public ResponseEntity<Object> createBook(@Valid @RequestBody RequestCreateBookDto dto) throws URISyntaxException {
		
		Book book = dto.convertDTOToEntity();
		Book bookToCreate = bookServiceImpl.save(book);
		
		ResponseCreateBookDto response = ResponseCreateBookDto.builder()
				.title(bookToCreate.getTitle())
				.quantity(bookToCreate.getQuantity())
				.build();
		
		return ResponseEntity.created(new URI("/livros" + book.getBookId())).body(response);
		
	}
	 @PutMapping("/{bookId}")
	    public ResponseEntity<Object> updateLivro(@PathVariable Long bookId, @RequestBody Book book) {
	        Book currentBook = bookServiceImpl.findById(bookId).orElseThrow(RuntimeException::new);
							        currentBook.setTitle(book.getTitle());
							        currentBook.setBorrow(book.getBorrow());
							        currentBook.setCategory(book.getCategory());
							        currentBook.setQuantity(book.getQuantity());
							        currentBook.setUpdatedAt(LocalDateTime.now());
							        currentBook.setBorrow(book.getBorrow());
							        
			bookServiceImpl.save(currentBook);
	        return ResponseEntity.ok(currentBook);
	    }

	    @DeleteMapping("/{bookId}")
	    public ResponseEntity<Object> deleteLivro(@PathVariable Long bookId) {
	        bookServiceImpl.deleteBookById(bookId);
	        return ResponseEntity.ok().build();
	    }

}
