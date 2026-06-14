package team.mowho.backend.domain.membercategory.application.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mowho.backend.domain.category.domain.Category;
import team.mowho.backend.domain.category.domain.CategoryRepository;
import team.mowho.backend.domain.category.domain.exception.CategoryNotFoundException;
import team.mowho.backend.domain.member.domain.MemberRepository;
import team.mowho.backend.domain.member.domain.exception.MemberNotFoundException;
import team.mowho.backend.domain.membercategory.application.dto.request.SelectCategoryServiceRequest;
import team.mowho.backend.domain.membercategory.application.dto.response.SelectCategoryResponse;
import team.mowho.backend.domain.membercategory.domain.MemberCategory;
import team.mowho.backend.domain.membercategory.domain.MemberCategoryRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberCategoryCommandService {

    private final MemberCategoryRepository memberCategoryRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public SelectCategoryResponse selectCategories(SelectCategoryServiceRequest request) {
        memberRepository.findWithLockByMemberId(request.memberId())
                .orElseThrow(MemberNotFoundException::new);
        validateCategories(request.categoryIds());
        List<MemberCategory> savedCategories = replaceCategories(request.memberId(), request.categoryIds());

        return SelectCategoryResponse.builder()
                .categoryIds(savedCategories.stream()
                        .map(MemberCategory::getCategoryId)
                        .toList())
                .build();
    }

    private void validateCategories(List<Long> categoryIds) {
        List<Long> foundIds = categoryRepository.findAllByCategoryIds(categoryIds)
                .stream()
                .map(Category::getId)
                .toList();

        if (foundIds.size() != categoryIds.stream().distinct().count()) {
            throw new CategoryNotFoundException();
        }
    }

    private List<MemberCategory> replaceCategories(Long memberId, List<Long> categoryIds) {
        memberCategoryRepository.deleteAllByMemberId(memberId);

        List<MemberCategory> memberCategories = categoryIds.stream()
                .distinct()
                .map(categoryId -> MemberCategory.of(memberId, categoryId))
                .toList();

        return memberCategoryRepository.saveAll(memberCategories);
    }

}
