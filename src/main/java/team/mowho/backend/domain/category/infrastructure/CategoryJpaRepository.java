package team.mowho.backend.domain.category.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.mowho.backend.domain.category.domain.Category;

import java.util.List;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.id IN :categoryIds")
    List<Category> findAllByCategoryIds(@Param("categoryIds") List<Long> categoryIds);

}

