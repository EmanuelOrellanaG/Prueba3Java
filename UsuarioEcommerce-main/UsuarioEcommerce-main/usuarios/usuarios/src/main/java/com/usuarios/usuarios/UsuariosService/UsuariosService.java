package com.usuarios.usuarios.UsuariosService;

import com.usuarios.usuarios.UsuariosDTO.UsuariosDTO;
import com.usuarios.usuarios.UsuariosEntity.UsuariosEntity;
import com.usuarios.usuarios.UsuariosRepository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuariosService {
    private final UsuarioRepository repository;

    public UsuariosEntity guardar(UsuariosDTO dto){

        UsuariosEntity usuario = new UsuariosEntity();

        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setPassword(dto.getPassword());

        return repository.save(usuario);
    }

    public List<UsuariosEntity> listar(){
        return repository.findAll();
    }

}
