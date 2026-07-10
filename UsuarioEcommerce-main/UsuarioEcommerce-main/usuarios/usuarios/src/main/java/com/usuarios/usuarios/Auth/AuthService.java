package com.usuarios.usuarios.Auth;

import com.usuarios.usuarios.UsuariosEntity.UsuariosEntity;
import com.usuarios.usuarios.UsuariosRepository.UsuarioRepository;
import com.usuarios.usuarios.Security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final JWTService jwtService;

    public String login(LoginDTO dto){

        UsuariosEntity usuario =
                repository.findByCorreo(dto.getCorreo())
                        .orElseThrow(() ->
                                new RuntimeException("Usuario no existe"));

        if(!usuario.getPassword().equals(dto.getPassword())){
            throw new RuntimeException("Password incorrecta");
        }

        return jwtService.generarToken(usuario.getCorreo());
    }
}
