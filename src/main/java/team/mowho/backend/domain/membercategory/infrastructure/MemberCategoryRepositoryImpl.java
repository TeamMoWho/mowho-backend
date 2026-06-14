package team.mowho.backend.domain.membercategory.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mowho.backend.domain.membercategory.domain.MemberCategory;
import team.mowho.backend.domain.membercategory.domain.MemberCategoryRepository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberCategoryRepositoryImpl implements MemberCategoryRepository {

    private final MemberCategoryJpaRepository memberCategoryJpaRepository;

    @Override
    public List<MemberCategory> saveAll(List<MemberCategory> memberCategories) {
        return memberCategoryJpaRepository.saveAll(memberCategories);
    }

    @Override
    public void deleteAllByMemberId(Long memberId) {
        memberCategoryJpaRepository.deleteAllByMemberId(memberId);
    }

    @Override
    public List<MemberCategory> findAllByMemberId(Long memberId) {
        return memberCategoryJpaRepository.findAllByMemberId(memberId);
    }

}
