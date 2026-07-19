package com.usuarios.usuarios.UsuariosController;

import com.usuarios.usuarios.UsuariosDTO.UsuariosDTO;
import com.usuarios.usuarios.UsuariosEntity.UsuariosEntity;
import com.usuarios.usuarios.UsuariosService.UsuariosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuariosController {
    private final UsuariosService service;

    @PostMapping
    public UsuariosEntity guardar(@RequestBody UsuariosDTO dto){
        return service.guardar(dto);
    }

    @GetMapping("/obtener")
    public List<UsuariosEntity> listar(){
        return service.listar();
    }

    @GetMapping("/hola")
    public String hola (){
        return "hola";
    }

    @GetMapping("/chao")
    public String chao (){
        return "chao";
    }
}
