package com.br.example.prj.biblioteca.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.example.prj.biblioteca.models.Student;

public interface StudentService {

	Page<Student> findAll(Pageable pageable);

	Page<Student> findByNameContaining(String title, Pageable pageable);

	Student save(Student student);

	Optional<Student> findById(Long studentId);

	void deleteStudentById(Long studentId);

}
