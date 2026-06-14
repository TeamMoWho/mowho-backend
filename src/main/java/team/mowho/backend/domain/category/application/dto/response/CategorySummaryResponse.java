package team.mowho.backend.domain.category.application.dto.response;

import lombok.Builder;
import team.mowho.backend.domain.category.domain.Category;

import java.util.List;

@Builder
public record CategorySummaryResponse(

        List<CategoryDetailResponse> categories

) {

    public static CategorySummaryResponse from(List<Category> categories) {
        return CategorySummaryResponse.builder()
                .categories(categories.stream()
                        .map(CategoryDetailResponse::from)
                        .toList())
                .build();
    }

}
