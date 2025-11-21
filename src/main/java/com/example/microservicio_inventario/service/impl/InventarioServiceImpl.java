package com.example.microservicio_inventario.service.impl;

import com.example.microservicio_inventario.model.Inventario;
import com.example.microservicio_inventario.repository.InventarioRepository;
import com.example.microservicio_inventario.service.InventarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioServiceImpl(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    @Transactional
    public Inventario crearInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    @Transactional(readOnly = true)
    public Inventario obtenerInventarioPorId(Long id) {
        return inventarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Inventario no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Inventario> obtenerTodoElInventario() {
        return inventarioRepository.findAll();
    }

    @Override
    @Transactional
    public Inventario actualizarInventario(Long id, Inventario inventarioDetalles) {
    Inventario inventarioExistente = obtenerInventarioPorId(id);
    inventarioExistente.setStock(inventarioDetalles.getStock());
    inventarioExistente.setMinimoStock(inventarioDetalles.getMinimoStock());
    return inventarioRepository.save(inventarioExistente);
    }

    @Override
    @Transactional
    public void eliminarInventario(Long id) {
        inventarioRepository.deleteById(id);
    }
}