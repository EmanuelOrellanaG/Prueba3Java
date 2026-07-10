package com.usuarios.usuarios.UsuariosRepository;

import com.usuarios.usuarios.UsuariosEntity.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuariosEntity,Long> {

    Optional<UsuariosEntity> findByCorreo(String correo);
}
