package com.br.example.prj.biblioteca.services.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.example.prj.biblioteca.models.Student;
import com.br.example.prj.biblioteca.repositories.StudentRepository;
import com.br.example.prj.biblioteca.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public Page<Student> findAll(Pageable pageable) {

		return studentRepository.findAll(pageable);
	}

	@Override
	public Page<Student> findByNameContaining(String name, Pageable pageable) {
		return studentRepository.findByNameContaining(name, pageable);
	}

	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Optional<Student> findById(Long studentId) {
		return studentRepository.findById(studentId);
	}

	@Override
	public void deleteStudentById(Long studentId) {
		studentRepository.deleteById(studentId);
	}

}
