package team.mowho.backend.domain.category.application.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mowho.backend.domain.category.application.dto.response.CategorySummaryResponse;
import team.mowho.backend.domain.category.domain.CategoryRepository;

@RequiredArgsConstructor
@Service
public class CategoryQueryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public CategorySummaryResponse getCategories() {
        return CategorySummaryResponse.from(categoryRepository.findAll());
    }

}
