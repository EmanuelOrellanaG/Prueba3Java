package com.pedidos.pedidos.PedidosService;
import com.pedidos.pedidos.PedidosDTO.ItemPedidoDTO;
import com.pedidos.pedidos.PedidosDTO.PedidosDTO;
import com.pedidos.pedidos.PedidosEntity.PedidoDetalleEntity;
import com.pedidos.pedidos.PedidosEntity.PedidosEntity;
import com.pedidos.pedidos.PedidosRepository.PedidosRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PedidosService {

    private final PedidosRepository repository;
    private final WebClient.Builder webClientBuilder;
    private final HttpServletRequest request;

    @Value("${producto.service.url}")
    private String productoServiceUrl;

    public PedidosEntity guardar(PedidosDTO dto) {

        String token = request.getHeader("Authorization");

        PedidosEntity pedidos = new PedidosEntity();

        pedidos.setUsuarioId(dto.getUsuarioId());
        pedidos.setEstado(dto.getEstado());
        pedidos.setFecha(LocalDate.now());

        List<PedidoDetalleEntity> detalles = new ArrayList<>();

        double totalPedido = 0;

        for (ItemPedidoDTO item : dto.getItems()) {

            PedidoDetalleEntity detalle = new PedidoDetalleEntity();
            detalle.setProductoId(item.getProductoId());
            detalle.setCantidad(item.getCantidad());

            try {

                @SuppressWarnings("unchecked")
                Map<String, Object> producto = webClientBuilder
                        .baseUrl(productoServiceUrl)
                        .build()
                        .get()
                        .uri("/productos/buscar/{id}", item.getProductoId())
                        .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(Map.class)
                        .block();

                if (producto != null) {

                    if (producto.get("precio") != null) {

                        double precio = ((Number) producto.get("precio")).doubleValue();

                        detalle.setPrecioUnitario(precio);

                        double subtotal = precio * item.getCantidad();

                        detalle.setSubtotal(subtotal);

                        totalPedido += subtotal;

                    } else {
                        throw new RuntimeException("El precio del producto no es válido");
                    }

                    if (producto.get("nombre") != null) {

                        detalle.setNombreProducto(producto.get("nombre").toString());

                    } else {

                        detalle.setNombreProducto("Producto sin nombre");
                    }

                } else {

                    throw new RuntimeException(
                            "El producto no fue encontrado en el microservicio"
                    );
                }

            } catch (Exception e) {

                throw new RuntimeException(
                        "Error al conectar con el servicio de Productos o producto inexistente: "
                                + e.getMessage()
                );
            }

            detalles.add(detalle);
        }

        pedidos.setDetalles(detalles);
        pedidos.setTotal(totalPedido);

        return repository.save(pedidos);
    }

    public List<PedidosEntity> listar() {
        return repository.findAll();
    }
}