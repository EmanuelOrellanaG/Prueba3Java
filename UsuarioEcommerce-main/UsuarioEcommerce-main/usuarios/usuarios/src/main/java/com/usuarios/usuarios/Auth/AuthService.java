package com.usuarios.usuarios.Auth;

import org.springframework.security.crypto.password.PasswordEncoder;
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
   private final PasswordEncoder passwordEncoder;

    public String login(LoginDTO dto){

        UsuariosEntity usuario =
                repository.findByCorreo(dto.getCorreo())
                        .orElseThrow(() ->
                                new RuntimeException("Usuario no existe"));

        if(!passwordEncoder.matches(
		dto.getPassword(),
		usuario.getPassword()
	)){
	  throw new RuntimeException("Password incorrecta");
	}

        return jwtService.generarToken(usuario.getCorreo());
    }
}
