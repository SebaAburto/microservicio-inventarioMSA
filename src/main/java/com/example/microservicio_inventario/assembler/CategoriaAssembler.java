package com.example.microservicio_inventario.assembler;

import com.example.microservicio_inventario.model.Categoria;
import com.example.microservicio_inventario.DTO.CategoriaDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoriaAssembler {

    public CategoriaDTO toDto(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        dto.setSlug(categoria.getSlug());
        return dto;
    }

    public Categoria toModel(CategoriaDTO dto) {
        Categoria model = new Categoria();
        model.setNombre(dto.getNombre());
        model.setSlug(dto.getSlug());
        return model;
    }

    public List<CategoriaDTO> toDtoList(List<Categoria> categorias) {
        return categorias.stream().map(this::toDto).collect(Collectors.toList());
    }
}