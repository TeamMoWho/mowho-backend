package team.mowho.backend.global.jwt.resolver;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import team.mowho.backend.global.jwt.TokenType;
import team.mowho.backend.global.jwt.properties.TokenProperties;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JwtTokenResolver {

    private static final String BEARER_PREFIX = "Bearer ";

    private final SecretKey secretKey;

    public JwtTokenResolver(TokenProperties tokenProperties) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenProperties.secretKey()));
    }

    public Optional<String> resolveTokenFromRequest(HttpServletRequest request) {
        return resolveFromHeader(request)
                .or(() -> resolveFromCookie(request, TokenType.ACCESS));
    }

    public Optional<String> resolveRefreshTokenFromRequest(HttpServletRequest request) {
        return resolveFromCookie(request, TokenType.REFRESH);
    }

    public String getSubjectFromToken(String token) {
        return getClaims(token).getPayload().getSubject();
    }

    public boolean isAccessToken(String token) {
        String type = getClaims(token).getPayload().get("type", String.class);
        return TokenType.ACCESS.getClaimValue().equals(type);
    }

    private static Optional<String> resolveFromHeader(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(AUTHORIZATION))
                .filter(auth -> StringUtils.hasText(auth) && auth.startsWith(BEARER_PREFIX))
                .map(auth -> auth.substring(BEARER_PREFIX.length()).trim())
                .filter(StringUtils::hasText);
    }

    private Optional<String> resolveFromCookie(HttpServletRequest request, TokenType tokenType) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return Optional.empty();
        }

        return Arrays.stream(cookies)
                .filter(cookie -> Objects.equals(cookie.getName(), tokenType.getCookieName()))
                .map(Cookie::getValue)
                .findFirst();
    }

    private Jws<Claims> getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
    }

}
