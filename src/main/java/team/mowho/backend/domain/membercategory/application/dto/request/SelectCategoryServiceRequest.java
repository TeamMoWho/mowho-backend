package team.mowho.backend.domain.membercategory.application.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record SelectCategoryServiceRequest(

        Long memberId,

        List<Long> categoryIds

) {
}
