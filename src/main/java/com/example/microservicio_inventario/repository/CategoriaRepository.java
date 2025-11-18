package microservicio_inventario.repository;

import microservicio_inventario.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}