package microservicio_inventario.service.impl;

import microservicio_inventario.model.Categoria;
import microservicio_inventario.repository.CategoriaRepository;
import microservicio_inventario.service.CategoriaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    @Transactional
    public Categoria crearCategoria(Categoria categoria) {
        // En una aplicación real, aquí podrías tener lógica para generar el slug si viene vacío.
        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll();
    }
    
    @Override
    @Transactional
    public Categoria actualizarCategoria(Long id, Categoria categoriaDetalles) {
        Categoria categoriaExistente = obtenerCategoriaPorId(id);
        categoriaExistente.setNombre(categoriaDetalles.getNombre());
        categoriaExistente.setSlug(categoriaDetalles.getSlug());
        return categoriaRepository.save(categoriaExistente);
    }

    @Override
    @Transactional
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}