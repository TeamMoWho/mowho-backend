package team.mowho.backend.domain.membercategory.domain;

import java.util.List;

public interface MemberCategoryRepository {

    void saveAll(List<MemberCategory> memberCategories);

    void deleteAllByMemberId(Long memberId);

    List<MemberCategory> findAllByMemberId(Long memberId);

}
