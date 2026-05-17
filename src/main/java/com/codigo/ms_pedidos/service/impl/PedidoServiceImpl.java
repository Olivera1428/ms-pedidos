package com.codigo.ms_pedidos.service.impl;

import com.codigo.ms_pedidos.dto.ActualizarEstadoDto;
import com.codigo.ms_pedidos.dto.PedidoRequestDto;
import com.codigo.ms_pedidos.dto.PedidoResponseDto;
import com.codigo.ms_pedidos.entity.Pedido;
import com.codigo.ms_pedidos.enums.EstadoPedido;
import com.codigo.ms_pedidos.exception.PedidoNotFoundException;
import com.codigo.ms_pedidos.repository.PedidoRepository;
import com.codigo.ms_pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Override
    public PedidoResponseDto crearPedido(PedidoRequestDto request) {

        BigDecimal total = request.getPrecioUnitario()
                .multiply(BigDecimal.valueOf(request.getCantidad()));

        Pedido pedido = Pedido.builder()
                .cliente(request.getCliente())
                .correoCliente(request.getCorreoCliente())
                .productoId(request.getProductoId())
                .nombreProducto(request.getNombreProducto())
                .cantidad(request.getCantidad())
                .precioUnitario(request.getPrecioUnitario())
                .total(total)
                .estado(EstadoPedido.REGISTRADO)
                .fechaPedido(LocalDateTime.now())
                .build();

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        return convertirDto(pedidoGuardado);
    }

    @Override
    public List<PedidoResponseDto> listarPedidos() {

        return pedidoRepository.findAll()
                .stream()
                .map(this::convertirDto)
                .toList();
    }

    @Override
    public PedidoResponseDto obtenerPedidoPorId(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException(
                        "No existe pedido con ID " + id
                ));

        return convertirDto(pedido);
    }

    @Override
    public PedidoResponseDto actualizarEstadoPedido(
            Long id,
            ActualizarEstadoDto request
    ) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException(
                        "No existe pedido con ID " + id
                ));

        pedido.setEstado(request.getEstado());

        Pedido actualizado = pedidoRepository.save(pedido);

        return convertirDto(actualizado);
    }

    @Override
    public void eliminarPedido(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException(
                        "No existe pedido con ID " + id
                ));

        pedidoRepository.delete(pedido);
    }

    private PedidoResponseDto convertirDto(Pedido pedido) {

        return PedidoResponseDto.builder()
                .id(pedido.getId())
                .cliente(pedido.getCliente())
                .correoCliente(pedido.getCorreoCliente())
                .productoId(pedido.getProductoId())
                .nombreProducto(pedido.getNombreProducto())
                .cantidad(pedido.getCantidad())
                .precioUnitario(pedido.getPrecioUnitario())
                .total(pedido.getTotal())
                .estado(pedido.getEstado())
                .fechaPedido(pedido.getFechaPedido())
                .build();
    }
}