package team.mowho.backend.domain.membercategory.application.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mowho.backend.domain.category.domain.CategoryRepository;
import team.mowho.backend.domain.category.domain.exception.CategoryNotFoundException;
import team.mowho.backend.domain.membercategory.application.request.SelectCategoryServiceRequest;
import team.mowho.backend.domain.membercategory.domain.MemberCategory;
import team.mowho.backend.domain.membercategory.domain.MemberCategoryRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberCategoryCommandService {

    private final MemberCategoryRepository memberCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void selectCategories(SelectCategoryServiceRequest request) {
        validateCategories(request.categoryIds());
        replaceCategories(request.memberId(), request.categoryIds());
    }

    private void validateCategories(List<Long> categoryIds) {
        categoryIds.forEach(categoryId -> {
            if (!categoryRepository.existsByCategoryId(categoryId)) {
                throw new CategoryNotFoundException();
            }
        });
    }

    private void replaceCategories(Long memberId, List<Long> categoryIds) {
        memberCategoryRepository.deleteAllByMemberId(memberId);

        List<MemberCategory> memberCategories = categoryIds.stream()
                .map(categoryId -> MemberCategory.of(memberId, categoryId))
                .toList();

        memberCategoryRepository.saveAll(memberCategories);
    }

}
