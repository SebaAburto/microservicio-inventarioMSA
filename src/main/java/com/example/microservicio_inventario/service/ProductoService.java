package com.example.microservicio_inventario.service;

import com.example.microservicio_inventario.model.Producto;
import java.util.List;

public interface ProductoService {
    Producto crearProducto(Producto producto, Long categoriaId, Long inventarioId);
    Producto obtenerProductoPorId(Long id);
    List<Producto> obtenerTodosLosProductos();
    Producto actualizarProducto(Long id, Producto productoDetalles);
    void eliminarProducto(Long id);
    
    // Métodos que usan las consultas derivadas del Repository
    List<Producto> buscarPorCategoria(String categoriaNombre);
    List<Producto> buscarPorNombre(String nombre);
}