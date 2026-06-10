package team.mowho.backend.domain.category.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mowho.backend.domain.category.domain.Category;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {
}
