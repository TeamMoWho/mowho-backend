package team.mowho.backend.domain.auth.application.auth.command;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.mowho.backend.domain.auth.application.dto.request.LoginServiceRequest;
import team.mowho.backend.domain.auth.domain.exception.LoginFailedException;
import team.mowho.backend.domain.auth.domain.refreshtoken.RefreshToken;
import team.mowho.backend.domain.auth.domain.refreshtoken.RefreshTokenRepository;
import team.mowho.backend.domain.member.domain.Member;
import team.mowho.backend.domain.member.domain.MemberRepository;
import team.mowho.backend.domain.member.domain.exception.MemberNotFoundException;
import team.mowho.backend.domain.member.domain.exception.PasswordNotMatchException;
import team.mowho.backend.global.jwt.cookie.TokenCookieManager;
import team.mowho.backend.global.jwt.dto.response.TokenResponse;
import team.mowho.backend.global.jwt.generator.JwtTokenGenerator;
import team.mowho.backend.global.jwt.properties.TokenProperties;

@RequiredArgsConstructor
@Service
public class AuthCommandService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenGenerator tokenGenerator;
    private final TokenCookieManager tokenCookieManager;
    private final TokenProperties tokenProperties;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse login(LoginServiceRequest request, HttpServletResponse response) {
        Member member = memberRepository.findByLoginId(request.loginId())
                .orElseThrow(LoginFailedException::new);

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new LoginFailedException();
        }

        String accessToken = tokenGenerator.generateAccessToken(member.getId());
        String refreshToken = tokenGenerator.generateRefreshToken(member.getId());

        RefreshToken refreshTokenEntity = refreshTokenRepository.findByMemberId(member.getId())
                .map(entity -> {
                    entity.rotate(refreshToken, tokenProperties.expirationTime().refreshToken());
                    return entity;
                })
                .orElseGet(() -> RefreshToken.of(member.getId(), refreshToken, tokenProperties.expirationTime().refreshToken()));

        refreshTokenRepository.save(refreshTokenEntity);

        TokenResponse tokenResponse = new TokenResponse(accessToken, refreshToken);
        tokenCookieManager.injectTokensToCookie(tokenResponse, response);

        return tokenResponse;
    }

}
