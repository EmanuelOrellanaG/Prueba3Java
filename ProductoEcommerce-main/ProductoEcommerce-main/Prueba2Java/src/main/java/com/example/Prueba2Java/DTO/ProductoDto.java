package com.example.Prueba2Java.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductoDto {

    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

}

