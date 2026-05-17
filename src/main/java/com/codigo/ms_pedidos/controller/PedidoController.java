package com.codigo.ms_productos.controller;

import com.codigo.ms_productos.dto.ProductoRequestDto;
import com.codigo.ms_productos.dto.ProductoResponseDto;
import com.codigo.ms_productos.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoResponseDto> crearProducto(
            @Valid @RequestBody ProductoRequestDto request
    ) {

        return new ResponseEntity<>(
                productoService.crearProducto(request),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDto>> listarProductos() {

        return ResponseEntity.ok(
                productoService.listarProductos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> obtenerProductoPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                productoService.obtenerProductoPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDto request
    ) {

        return ResponseEntity.ok(
                productoService.actualizarProducto(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(
            @PathVariable Long id
    ) {

        productoService.eliminarProducto(id);

        return ResponseEntity.ok(
                "Producto eliminado correctamente"
        );
    }
}
