package team.mowho.backend.domain.membercategory.domain;

import java.util.List;

public interface MemberCategoryRepository {

    List<MemberCategory> saveAll(List<MemberCategory> memberCategories);

    void deleteAllByMemberId(Long memberId);

    List<MemberCategory> findAllByMemberId(Long memberId);

}
