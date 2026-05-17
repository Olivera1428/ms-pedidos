package com.codigo.ms_pedidos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoRequestDto {

    @NotBlank(message = "El cliente no debe estar vacío")
    private String cliente;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener formato válido")
    private String correoCliente;

    @NotNull(message = "El productoId es obligatorio")
    private Long productoId;

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombreProducto;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor que cero")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor que cero")
    private BigDecimal precioUnitario;
}