package microservicio_inventario.service.impl;

import microservicio_inventario.model.Producto;
import microservicio_inventario.repository.ProductoRepository;
import microservicio_inventario.repository.CategoriaRepository;
import microservicio_inventario.repository.InventarioRepository;
import microservicio_inventario.service.ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final InventarioRepository inventarioRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository, InventarioRepository inventarioRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    @Transactional
    public Producto crearProducto(Producto producto, Long categoriaId, Long inventarioId) {
        var categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada con ID: " + categoriaId));
        
        var inventario = inventarioRepository.findById(inventarioId)
                .orElseThrow(() -> new NoSuchElementException("Inventario no encontrado con ID: " + inventarioId));

        producto.setCategoria(categoria);
        producto.setInventario(inventario);
        
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional
    public Producto actualizarProducto(Long id, Producto productoDetalles) {
        Producto productoExistente = obtenerProductoPorId(id);
        
        productoExistente.setSku(productoDetalles.getSku());
        productoExistente.setNombre(productoDetalles.getNombre());
        productoExistente.setDescripcion(productoDetalles.getDescripcion());
        productoExistente.setPrecio(productoDetalles.getPrecio());
        productoExistente.setImagen(productoDetalles.getImagen());
        productoExistente.setPrecioOferta(productoDetalles.getPrecioOferta());
        productoExistente.setDestacado(productoDetalles.getDestacado());

        return productoRepository.save(productoExistente);
    }

    @Override
    @Transactional
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarPorCategoria(String categoriaNombre) {
        return productoRepository.findByCategoriaNombre(categoriaNombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}