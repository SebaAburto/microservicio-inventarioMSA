package com.example.microservicio_inventario.DTO;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class InventarioDTO {
    private Long id;
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;
    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private Integer minimoStock;
}