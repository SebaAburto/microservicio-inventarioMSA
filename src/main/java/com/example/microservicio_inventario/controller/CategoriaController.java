package microservicio_inventario.controller;

import microservicio_inventario.model.Categoria;
import microservicio_inventario.dto.CategoriaDTO;
import microservicio_inventario.assembler.CategoriaAssembler;
import microservicio_inventario.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final CategoriaAssembler categoriaAssembler;

    public CategoriaController(CategoriaService categoriaService, CategoriaAssembler categoriaAssembler) {
        this.categoriaService = categoriaService;
        this.categoriaAssembler = categoriaAssembler;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> obtenerTodasLasCategorias() {
        List<Categoria> modelos = categoriaService.obtenerTodasLasCategorias();
        return ResponseEntity.ok(categoriaAssembler.toDtoList(modelos));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerCategoriaPorId(@PathVariable Long id) {
        Categoria modelo = categoriaService.obtenerCategoriaPorId(id);
        return ResponseEntity.ok(categoriaAssembler.toDto(modelo));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> crearCategoria(@Valid @RequestBody CategoriaDTO categoriaDto) {
        Categoria modelEntrada = categoriaAssembler.toModel(categoriaDto);
        Categoria modelSalida = categoriaService.crearCategoria(modelEntrada);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaAssembler.toDto(modelSalida));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaDTO categoriaDto) {
        // Mapeamos el DTO a Modelo para pasarlo al Service
        Categoria categoriaDetalles = categoriaAssembler.toModel(categoriaDto);
        
        Categoria categoriaActualizada = categoriaService.actualizarCategoria(id, categoriaDetalles);
        return ResponseEntity.ok(categoriaAssembler.toDto(categoriaActualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        // Retorna 204 No Content
        return ResponseEntity.noContent().build();
    }
}