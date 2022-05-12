package com.br.example.prj.biblioteca.DTO.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.br.example.prj.biblioteca.models.Student;
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
public class RequestCreateStudentDto {

	@NotNull(message = "Nome não pode ser nulo")
	private String name;

	private String grade;

	private String email;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", locale = "en/US", timezone = "Brasil/East")
	private LocalDateTime createdAt = LocalDateTime.now();

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", locale = "en/US", timezone = "Brasil/East")
	private LocalDateTime updatedAt = LocalDateTime.now();

	public Student convertDTOToEntity() {
		return new Student(name, grade, email, createdAt, updatedAt);

	}

}
