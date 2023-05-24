package nguyennhathao.example.lap3456.repository;

import nguyennhathao.example.lap3456.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
}
