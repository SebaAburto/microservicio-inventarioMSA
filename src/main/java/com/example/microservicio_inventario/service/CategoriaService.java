package com.example.microservicio_inventario.service;

import com.example.microservicio_inventario.model.Categoria;
import java.util.List;

public interface CategoriaService {
    Categoria crearCategoria(Categoria categoria);
    Categoria obtenerCategoriaPorId(Long id);
    List<Categoria> obtenerTodasLasCategorias();
    Categoria actualizarCategoria(Long id, Categoria categoriaDetalles);
    void eliminarCategoria(Long id);
    
}