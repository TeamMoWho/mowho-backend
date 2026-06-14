package team.mowho.backend.domain.membercategory.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.mowho.backend.domain.membercategory.domain.MemberCategory;

import java.util.List;

public interface MemberCategoryJpaRepository extends JpaRepository<MemberCategory, Long> {

    List<MemberCategory> findAllByMemberId(Long memberId);

    @Modifying
    @Query("DELETE FROM MemberCategory mc WHERE mc.memberId = :memberId")
    void deleteAllByMemberId(@Param("memberId") Long memberId);

}
