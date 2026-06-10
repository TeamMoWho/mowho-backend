package team.mowho.backend.domain.category.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mowho.backend.domain.category.domain.Category;
import team.mowho.backend.domain.category.domain.CategoryRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public Optional<Category> findByCategoryId(Long categoryId) {
        return categoryJpaRepository.findById(categoryId);
    }

    @Override
    public List<Category> findAll() {
        return categoryJpaRepository.findAll();
    }

    @Override
    public boolean existsByCategoryId(Long categoryId) {
        return categoryJpaRepository.existsById(categoryId);
    }

}
