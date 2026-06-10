package team.mowho.backend.domain.category.domain;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findByCategoryId(Long categoryId);

    List<Category> findAll();

    boolean existsByCategoryId(Long categoryId);

}
