package com.br.example.prj.biblioteca.DTO.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;

import com.br.example.prj.biblioteca.models.Book;
import com.br.example.prj.biblioteca.models.Enum.BorrowEnum;
import com.br.example.prj.biblioteca.models.Enum.CategoryEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestUpdateBookDto {
	
	@NotNull(message = "Título não pode ser nulo")
	private String title;

	
	public Book convertDTOToEntity() {
		return new Book(title);
	
	}

}
