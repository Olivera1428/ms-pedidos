package com.codigo.ms_pedidos.service;

import com.codigo.ms_pedidos.dto.ActualizarEstadoDto;
import com.codigo.ms_pedidos.dto.PedidoRequestDto;
import com.codigo.ms_pedidos.dto.PedidoResponseDto;

import java.util.List;

public interface PedidoService {

    PedidoResponseDto crearPedido(PedidoRequestDto request);

    List<PedidoResponseDto> listarPedidos();

    PedidoResponseDto obtenerPedidoPorId(Long id);

    PedidoResponseDto actualizarEstadoPedido(
            Long id,
            ActualizarEstadoDto request
    );

    void eliminarPedido(Long id);
}