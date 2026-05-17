package com.codigo.ms_pedidos.dto;

import com.codigo.ms_pedidos.enums.EstadoPedido;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoResponseDto {

    private Long id;
    private String cliente;
    private String correoCliente;
    private Long productoId;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal total;
    private EstadoPedido estado;
    private LocalDateTime fechaPedido;
}