package com.br.example.prj.biblioteca.repositories;

import com.br.example.prj.biblioteca.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {

    Optional<Usuario> findByUsername(String username);

    Boolean existsByUsername(String email);

}
