package team.mowho.backend.domain.auth.infrastructure.refreshtoken;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mowho.backend.domain.auth.domain.refreshtoken.RefreshToken;
import team.mowho.backend.domain.auth.domain.refreshtoken.RefreshTokenRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenJpaRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByMemberId(Long memberId) {
        return refreshTokenJpaRepository.findByMemberId(memberId);
    }

    @Override
    public void deleteByMemberId(Long memberId) {
        refreshTokenJpaRepository.deleteByMemberId(memberId);
    }

}
