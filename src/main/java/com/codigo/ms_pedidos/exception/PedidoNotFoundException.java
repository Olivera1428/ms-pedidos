package com.codigo.ms_pedidos.exception;

public class PedidoNotFoundException extends RuntimeException {

    public PedidoNotFoundException(String mensaje) {
        super(mensaje);
    }
}