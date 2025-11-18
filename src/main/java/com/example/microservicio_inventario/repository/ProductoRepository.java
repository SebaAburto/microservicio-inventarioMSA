package microservicio_inventario.repository;

import microservicio_inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoriaNombre(String categoriaNombre);

    long countByCategoriaNombre(String categoriaNombre);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}