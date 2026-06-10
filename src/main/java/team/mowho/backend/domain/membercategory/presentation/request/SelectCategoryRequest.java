package team.mowho.backend.domain.membercategory.presentation.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import team.mowho.backend.domain.membercategory.application.dto.request.SelectCategoryServiceRequest;

import java.util.List;

@Builder
public record SelectCategoryRequest(

        @NotEmpty(message = "카테고리를 1개 이상 선택해주세요.")
        @Size(max = 25, message = "카테고리는 최대 25개까지 선택 가능합니다.")
        List<Long> categoryIds

) {

    public SelectCategoryServiceRequest toServiceRequest(Long memberId) {
        return SelectCategoryServiceRequest.builder()
                .memberId(memberId)
                .categoryIds(categoryIds)
                .build();
    }

}
