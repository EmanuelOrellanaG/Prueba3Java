package com.example.Prueba2Java.Service;

import com.example.Prueba2Java.DTO.ProductoDto;
import com.example.Prueba2Java.Entity.Producto;
import com.example.Prueba2Java.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoDto guardar(ProductoDto dto){

        Producto producto = new Producto();

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());

        Producto guardado = productoRepository.save(producto);

        return convertirADTO(guardado);
    }

    public List<ProductoDto> listar() {
        return productoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public ProductoDto buscarPorId(Long id){

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado"));

        return convertirADTO(producto);
    }

    public ProductoDto actualizar(Long id, ProductoDto dto){

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado"));

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());

        Producto actualizado = productoRepository.save(producto);

        return convertirADTO(actualizado);
    }

    public void eliminar(Long id){
        productoRepository.deleteById(id);
    }

    private ProductoDto convertirADTO(Producto producto){

        ProductoDto dto = new ProductoDto();

        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());

        return dto;
    }

}
