package microservicio_inventario.controller;

import microservicio_inventario.model.Producto;
import microservicio_inventario.dto.ProductoDTO;
import microservicio_inventario.assembler.ProductoAssembler;
import microservicio_inventario.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoAssembler productoAssembler;

    public ProductoController(ProductoService productoService, ProductoAssembler productoAssembler) {
        this.productoService = productoService;
        this.productoAssembler = productoAssembler;
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerTodosLosProductos() {
        List<Producto> modelos = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok(productoAssembler.toDtoList(modelos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id) {
        Producto modelo = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(productoAssembler.toDto(modelo));
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoDTO>> buscarProductosPorNombre(@RequestParam String nombre) {
        List<Producto> modelos = productoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(productoAssembler.toDtoList(modelos));
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDto) {
        Producto modelEntrada = productoAssembler.toModel(productoDto);
        
        Producto modelSalida = productoService.crearProducto(
            modelEntrada, 
            productoDto.getCategoriaId(), 
            productoDto.getInventarioId()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(productoAssembler.toDto(modelSalida));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDto) {
        Producto productoDetalles = productoAssembler.toModel(productoDto);
        Producto productoActualizado = productoService.actualizarProducto(id, productoDetalles);
        return ResponseEntity.ok(productoAssembler.toDto(productoActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        // Retorna 204 No Content
        return ResponseEntity.noContent().build();
    }
}