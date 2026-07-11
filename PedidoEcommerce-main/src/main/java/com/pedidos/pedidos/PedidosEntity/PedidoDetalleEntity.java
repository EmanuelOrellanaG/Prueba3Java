package com.pedidos.pedidos.PedidosEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido_detalles")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PedidoDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private Long productoId;
    @JsonProperty("nombreProducto")
    private String nombreProducto;
    private Integer cantidad;
    private double precioUnitario;
    private double subtotal;



}
