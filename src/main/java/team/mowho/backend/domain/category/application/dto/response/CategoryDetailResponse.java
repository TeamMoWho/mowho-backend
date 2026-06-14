package team.mowho.backend.domain.category.application.dto.response;

import lombok.Builder;
import team.mowho.backend.domain.category.domain.Category;

@Builder
public record CategoryDetailResponse(

        Long categoryId,

        String name

) {

    public static CategoryDetailResponse from(Category category) {
        return CategoryDetailResponse.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .build();
    }

}

