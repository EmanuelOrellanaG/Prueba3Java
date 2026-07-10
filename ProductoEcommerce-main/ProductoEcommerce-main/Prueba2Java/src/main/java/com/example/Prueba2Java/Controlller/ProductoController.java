package com.example.Prueba2Java.Controlller;

import com.example.Prueba2Java.DTO.ProductoDto;
import com.example.Prueba2Java.Entity.Producto;
import com.example.Prueba2Java.Service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor

public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ProductoDto guardar (@RequestBody ProductoDto productoDto){
        return productoService.guardar(productoDto);
    }

    @GetMapping
    public List<ProductoDto> listar(){
        return productoService.listar();
    }

    @GetMapping("/buscar/{id}")
    public ProductoDto buscar(@PathVariable Long id){
        return productoService.buscarPorId(id);
    }

    @PutMapping("/actualizar/{id}")
    public ProductoDto actualizar(@PathVariable Long id, @RequestBody ProductoDto dto){

        return productoService.actualizar(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminar(@PathVariable Long id){
        productoService.eliminar(id);
    }



}
