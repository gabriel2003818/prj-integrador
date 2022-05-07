package com.br.example.prj.biblioteca.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.example.prj.biblioteca.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Page<Student> findAll(Pageable pageable);

	Page<Student> findByNameContaining(String name, Pageable pageable);

}
