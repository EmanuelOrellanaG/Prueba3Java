package com.pedidos.pedidos.PedidosDTO;

import lombok.Data;

import java.util.List;

@Data
public class PedidosDTO {
    private Long usuarioId;
    private String estado;
    private List<ItemPedidoDTO> items;
}
