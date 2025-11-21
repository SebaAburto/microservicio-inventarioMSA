package com.example.microservicio_inventario.service;

import com.example.microservicio_inventario.model.Inventario;
import java.util.List;

public interface InventarioService {
    Inventario crearInventario(Inventario inventario);
    Inventario obtenerInventarioPorId(Long id);
    List<Inventario> obtenerTodoElInventario();
    Inventario actualizarInventario(Long id, Inventario inventarioDetalles);
    void eliminarInventario(Long id);
}