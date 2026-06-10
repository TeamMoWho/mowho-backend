package team.mowho.backend.domain.membercategory.application.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record SelectCategoryResponse(

        List<Long> categoryIds

) {
}
