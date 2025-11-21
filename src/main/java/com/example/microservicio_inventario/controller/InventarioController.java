package com.example.microservicio_inventario.controller;

import com.example.microservicio_inventario.model.Inventario;
import com.example.microservicio_inventario.DTO.InventarioDTO;
import com.example.microservicio_inventario.assembler.InventarioAssembler;
import com.example.microservicio_inventario.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {

    private final InventarioService inventarioService;
    private final InventarioAssembler inventarioAssembler;

    public InventarioController(InventarioService inventarioService, InventarioAssembler inventarioAssembler) {
        this.inventarioService = inventarioService;
        this.inventarioAssembler = inventarioAssembler;
    }

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> obtenerTodoElInventario() {
        List<Inventario> modelos = inventarioService.obtenerTodoElInventario();
        return ResponseEntity.ok(inventarioAssembler.toDtoList(modelos));
    }

    @PostMapping
    public ResponseEntity<InventarioDTO> crearInventario(@Valid @RequestBody InventarioDTO inventarioDto) {
        Inventario modelEntrada = inventarioAssembler.toModel(inventarioDto);
        Inventario modelSalida = inventarioService.crearInventario(modelEntrada);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioAssembler.toDto(modelSalida));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> actualizarInventario(@PathVariable Long id, @Valid @RequestBody InventarioDTO inventarioDto) {
        Inventario inventarioDetalles = inventarioAssembler.toModel(inventarioDto);
        
        inventarioDetalles.setId(id);
        
        Inventario inventarioActualizado = inventarioService.crearInventario(inventarioDetalles);
        return ResponseEntity.ok(inventarioAssembler.toDto(inventarioActualizado));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable Long id) {
        inventarioService.eliminarInventario(id);
        return ResponseEntity.noContent().build();
    }
}