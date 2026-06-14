package team.mowho.backend.domain.category.presentation.query;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.mowho.backend.domain.category.application.dto.response.CategorySummaryResponse;
import team.mowho.backend.domain.category.application.query.CategoryQueryService;

@RequiredArgsConstructor
@RequestMapping("/api/categories")
@RestController
public class CategoryQueryController {

    private final CategoryQueryService categoryQueryService;

    @GetMapping
    public ResponseEntity<CategorySummaryResponse> getCategories() {
        return ResponseEntity.ok(categoryQueryService.getCategories());
    }

}
