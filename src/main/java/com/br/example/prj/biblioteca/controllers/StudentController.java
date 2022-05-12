package com.br.example.prj.biblioteca.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.br.example.prj.biblioteca.DTO.request.RequestCreateStudentDto;
import com.br.example.prj.biblioteca.DTO.request.RequestUpdateBookDto;
import com.br.example.prj.biblioteca.DTO.response.ResponseCreateBookDto;
import com.br.example.prj.biblioteca.DTO.response.ResponseCreateStudentDto;
import com.br.example.prj.biblioteca.models.Book;
import com.br.example.prj.biblioteca.models.Student;
import com.br.example.prj.biblioteca.services.BookService;
import com.br.example.prj.biblioteca.services.StudentService;

@RestController
@RequestMapping("/alunos")
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllStudents(@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size) {

		try {

			List<Student> students = new ArrayList<Student>();
			Pageable paging = PageRequest.of(page, size);

			Page<Student> pageStudents = null;
			if (name == null)
				pageStudents = studentService.findAll(paging);
			else
				pageStudents = studentService.findByNameContaining(name, paging);

			students = pageStudents.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("students", students);
			response.put("currentPage", pageStudents.getNumber());
			response.put("totalItems", pageStudents.getTotalElements());
			response.put("totalPages", pageStudents.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/{studentId}")
	public ResponseEntity<Object> getStudent(@PathVariable Long studentId) {

		Optional<Student> currentStudent = studentService.findById(studentId);

		if (currentStudent.isPresent()) {
			return new ResponseEntity<>(currentStudent.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/add")
	public ResponseEntity<Object> createBook(@Valid @RequestBody RequestCreateStudentDto dto)
			throws URISyntaxException {

		Student student = dto.convertDTOToEntity();
		Student studentToCreate = studentService.save(student);

		ResponseCreateStudentDto response = ResponseCreateStudentDto.builder().name(studentToCreate.getName())
				.grade(studentToCreate.getGrade()).email(studentToCreate.getEmail()).build();

		return ResponseEntity.created(new URI("/alunos" + student.getStudentId())).body(response);

	}

	@PutMapping("/edit/{studentId}")
	public ResponseEntity<Object> updateStudent(@PathVariable Long studentId,
			@RequestBody RequestCreateStudentDto student) {
		Student currentStudent = studentService.findById(studentId).orElseThrow(RuntimeException::new);
			
		currentStudent.setName(student.getName());
		currentStudent.setGrade(student.getGrade());
		currentStudent.setEmail(student.getEmail());
		currentStudent.setUpdatedAt(LocalDateTime.now());
		
		studentService.save(currentStudent);
		return ResponseEntity.ok(currentStudent);
	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<Object> deleteLivro(@PathVariable Long studentId) {
		studentService.deleteStudentById(studentId);
		return ResponseEntity.ok().build();
	}

}
