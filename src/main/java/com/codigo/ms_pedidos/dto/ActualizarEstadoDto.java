package com.codigo.ms_pedidos.dto;

import com.codigo.ms_pedidos.enums.EstadoPedido;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarEstadoDto {

    @NotNull(message = "El estado es obligatorio")
    private EstadoPedido estado;
}