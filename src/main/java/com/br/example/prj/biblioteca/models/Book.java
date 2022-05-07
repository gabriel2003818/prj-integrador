package com.br.example.prj.biblioteca.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.br.example.prj.biblioteca.models.Enum.BorrowEnum;
import com.br.example.prj.biblioteca.models.Enum.CategoryEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookId;
	private String title;	
	@Enumerated(EnumType.STRING)
    private CategoryEnum category; 
	@Enumerated(EnumType.STRING)
	private BorrowEnum borrow;
	private Integer quantity;
	private LocalDateTime createdAt;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime updatedAt;
	
	
	public Book(String title, CategoryEnum category, BorrowEnum borrow, Integer quantity, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.title = title;
		this.category = category;
		this.borrow = borrow;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Book(String title) {
		this.title = title;
	}
	

	
}
