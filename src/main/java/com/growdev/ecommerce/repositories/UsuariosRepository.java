package com.growdev.ecommerce.repositories;

import com.growdev.ecommerce.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
