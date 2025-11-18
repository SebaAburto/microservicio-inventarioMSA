package microservicio_inventario.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    @NotBlank(message = "El SKU es obligatorio")
    private String sku;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    private String descripcion;
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a cero")
    private Double precio;
    private Double precioOferta;
    @NotBlank(message = "La referencia de imagen es obligatoria")
    private String imagen;
    private Boolean destacado;
    private Long categoriaId;
    private Long inventarioId;
}