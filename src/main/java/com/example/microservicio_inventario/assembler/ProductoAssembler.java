package microservicio_inventario.assembler;

import microservicio_inventario.model.Producto;
import microservicio_inventario.dto.ProductoDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductoAssembler {
    
    public ProductoDTO toDto(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setSku(producto.getSku());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        
        // Mapea el nuevo campo
        dto.setPrecioOferta(producto.getPrecioOferta()); 
        
        dto.setImagen(producto.getImagen());
        // Se omite enOferta
        dto.setDestacado(producto.getDestacado());
        if (producto.getCategoria() != null) {
            dto.setCategoriaId(producto.getCategoria().getId());
        }
        if (producto.getInventario() != null) {
            dto.setInventarioId(producto.getInventario().getId());
        }
        return dto;
    }

    public Producto toModel(ProductoDTO dto) {
        Producto model = new Producto();
        model.setSku(dto.getSku());
        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setPrecio(dto.getPrecio());
        
        // Mapea el nuevo campo
        model.setPrecioOferta(dto.getPrecioOferta()); 
        
        model.setImagen(dto.getImagen());
        // Se omite enOferta
        model.setDestacado(dto.getDestacado());
        return model;
    }
    
    public List<ProductoDTO> toDtoList(List<Producto> productos) {
        return productos.stream().map(this::toDto).collect(Collectors.toList());
    }
}