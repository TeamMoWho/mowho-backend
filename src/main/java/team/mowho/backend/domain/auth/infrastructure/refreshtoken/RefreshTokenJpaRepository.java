package team.mowho.backend.domain.auth.infrastructure.refreshtoken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.mowho.backend.domain.auth.domain.refreshtoken.RefreshToken;

import java.util.Optional;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByMemberId(Long memberId);

    @Modifying
    @Query("DELETE FROM RefreshToken rt WHERE rt.memberId = :memberId")
    void deleteByMemberId(@Param("memberId") Long memberId);

}
