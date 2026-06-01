package team.mowho.backend.domain.auth.infrastructure.refreshtoken;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mowho.backend.domain.auth.domain.refreshtoken.RefreshToken;

import java.util.Optional;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByMemberId(Long memberId);

    void deleteByMemberId(Long memberId);

}
