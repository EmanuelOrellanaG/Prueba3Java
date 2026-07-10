package com.example.Prueba2Java.repository;

import com.example.Prueba2Java.DTO.ProductoDto;
import com.example.Prueba2Java.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.aot.JpaRepositoryContributor;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductoRepository extends JpaRepository<Producto, Long> {


}