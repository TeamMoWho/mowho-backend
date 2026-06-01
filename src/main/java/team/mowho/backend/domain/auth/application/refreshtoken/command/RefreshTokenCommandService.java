package team.mowho.backend.domain.auth.application.refreshtoken.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mowho.backend.domain.auth.domain.exception.RefreshTokenNotFoundException;
import team.mowho.backend.domain.auth.domain.exception.RefreshTokenNotValidException;
import team.mowho.backend.domain.auth.domain.refreshtoken.RefreshToken;
import team.mowho.backend.domain.auth.domain.refreshtoken.RefreshTokenRepository;
import team.mowho.backend.global.jwt.cookie.TokenCookieManager;
import team.mowho.backend.global.jwt.dto.response.TokenResponse;
import team.mowho.backend.global.jwt.generator.JwtTokenGenerator;
import team.mowho.backend.global.jwt.properties.TokenProperties;
import team.mowho.backend.global.jwt.resolver.JwtTokenResolver;

@RequiredArgsConstructor
@Service
public class RefreshTokenCommandService {

    private final JwtTokenGenerator tokenGenerator;
    private final JwtTokenResolver tokenResolver;
    private final TokenCookieManager tokenCookieManager;
    private final TokenProperties tokenProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokenResponse reissue(HttpServletRequest request, HttpServletResponse response) {
        String refreshTokenStr = tokenResolver.resolveRefreshTokenFromRequest(request)
                .orElseThrow(RefreshTokenNotFoundException::new);

        Long memberId = parseMemberId(refreshTokenStr);

        RefreshToken savedRefreshToken = refreshTokenRepository.findByMemberId(memberId)
                .orElseThrow(RefreshTokenNotFoundException::new);

        savedRefreshToken.validate(refreshTokenStr, memberId);

        String newAccessToken = tokenGenerator.generateAccessToken(memberId);
        String newRefreshToken = tokenGenerator.generateRefreshToken(memberId);

        savedRefreshToken.rotate(newRefreshToken, tokenProperties.expirationTime().refreshToken());

        TokenResponse tokenResponse = new TokenResponse(newAccessToken, newRefreshToken);
        tokenCookieManager.injectTokensToCookie(tokenResponse, response);

        return tokenResponse;
    }

    private Long parseMemberId(String token) {
        try {
            return Long.parseLong(tokenResolver.getSubjectFromToken(token));
        } catch (Exception e) {
            throw new RefreshTokenNotValidException();
        }
    }

}
