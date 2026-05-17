package com.codigo.ms_pedidos.repository;

import com.codigo.ms_pedidos.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}