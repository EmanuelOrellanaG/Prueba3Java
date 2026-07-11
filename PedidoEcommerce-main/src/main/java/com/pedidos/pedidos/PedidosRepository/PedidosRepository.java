package com.pedidos.pedidos.PedidosRepository;

import com.pedidos.pedidos.PedidosEntity.PedidosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosRepository extends JpaRepository<PedidosEntity,Long> {
}
