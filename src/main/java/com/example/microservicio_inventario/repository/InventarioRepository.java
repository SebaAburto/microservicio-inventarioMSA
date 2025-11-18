package microservicio_inventario.repository;

import microservicio_inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}