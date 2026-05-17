package com.codigo.ms_pedidos.controller;

import com.codigo.ms_pedidos.dto.ActualizarEstadoDto;
import com.codigo.ms_pedidos.dto.PedidoRequestDto;
import com.codigo.ms_pedidos.dto.PedidoResponseDto;
import com.codigo.ms_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDto crearPedido(
            @Valid @RequestBody PedidoRequestDto request
    ) {

        return pedidoService.crearPedido(request);
    }

    @GetMapping
    public List<PedidoResponseDto> listarPedidos() {

        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public PedidoResponseDto obtenerPedidoPorId(@PathVariable Long id) {

        return pedidoService.obtenerPedidoPorId(id);
    }

    @PatchMapping("/{id}/estado")
    public PedidoResponseDto actualizarEstadoPedido(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarEstadoDto request
    ) {

        return pedidoService.actualizarEstadoPedido(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPedido(@PathVariable Long id) {

        pedidoService.eliminarPedido(id);
    }
}
