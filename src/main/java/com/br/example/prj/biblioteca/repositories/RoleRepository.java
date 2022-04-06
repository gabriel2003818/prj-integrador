package com.br.example.prj.biblioteca.repositories;

import com.br.example.prj.biblioteca.models.Enum.EnumRole;
import com.br.example.prj.biblioteca.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(EnumRole name);

}
