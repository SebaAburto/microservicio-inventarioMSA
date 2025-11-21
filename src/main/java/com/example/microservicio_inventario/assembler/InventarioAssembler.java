package com.example.microservicio_inventario.assembler;

import com.example.microservicio_inventario.model.Inventario;
import com.example.microservicio_inventario.DTO.InventarioDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InventarioAssembler {

    public InventarioDTO toDto(Inventario inventario) {
        InventarioDTO dto = new InventarioDTO();
        dto.setId(inventario.getId());
        dto.setStock(inventario.getStock());
        dto.setMinimoStock(inventario.getMinimoStock());
        return dto;
    }

    public Inventario toModel(InventarioDTO dto) {
        Inventario model = new Inventario();
        model.setStock(dto.getStock());
        model.setMinimoStock(dto.getMinimoStock());
        return model;
    }
    
    public List<InventarioDTO> toDtoList(List<Inventario> inventarios) {
        return inventarios.stream().map(this::toDto).collect(Collectors.toList());
    }
}