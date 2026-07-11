package com.pedidos.pedidos.PedidosController;

import com.pedidos.pedidos.PedidosDTO.PedidosDTO;
import com.pedidos.pedidos.PedidosEntity.PedidosEntity;
import com.pedidos.pedidos.PedidosService.PedidosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidosController {
    private final PedidosService service;

    @PostMapping
    public PedidosEntity guardar(@RequestBody PedidosDTO dto){
        return service.guardar(dto);
    }

    @GetMapping
    public List<PedidosEntity> listar(){
        return service.listar();
    }
}
