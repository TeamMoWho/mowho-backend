package team.mowho.backend.domain.membercategory.presentation.command;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.mowho.backend.domain.membercategory.application.command.MemberCategoryCommandService;
import team.mowho.backend.domain.membercategory.application.dto.response.SelectCategoryResponse;
import team.mowho.backend.domain.membercategory.presentation.request.SelectCategoryRequest;
import team.mowho.backend.global.resolver.annotation.MemberId;

@RequiredArgsConstructor
@RequestMapping("/api/members/me/categories")
@RestController
public class MemberCategoryCommandController {

    private final MemberCategoryCommandService memberCategoryCommandService;

    @PostMapping
    public ResponseEntity<SelectCategoryResponse> selectCategories(
            @MemberId Long memberId,
            @Valid @RequestBody SelectCategoryRequest request
    ) {
        SelectCategoryResponse response = memberCategoryCommandService.selectCategories(request.toServiceRequest(memberId));

        return ResponseEntity.ok(response);
    }

}
