package team.mowho.backend.global.jwt.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.mowho.backend.global.jwt.TokenType;
import team.mowho.backend.global.jwt.dto.response.TokenResponse;
import team.mowho.backend.global.jwt.properties.CookieProperties;
import team.mowho.backend.global.jwt.properties.TokenProperties;

@RequiredArgsConstructor
@Component
public class TokenCookieManager {

    private static final int COOKIE_EXPIRY_BUFFER_SECONDS = 5;

    private final TokenProperties tokenProperties;
    private final CookieProperties cookieProperties;

    public void injectTokensToCookie(TokenResponse tokenResponse, HttpServletResponse response) {
        addCookie(
                TokenType.ACCESS.getCookieName(), tokenResponse.accessToken(),
                Math.toIntExact(tokenProperties.expirationTime().accessToken() + COOKIE_EXPIRY_BUFFER_SECONDS), response
        );
        addCookie(
                TokenType.REFRESH.getCookieName(), tokenResponse.refreshToken(),
                Math.toIntExact(tokenProperties.expirationTime().refreshToken() + COOKIE_EXPIRY_BUFFER_SECONDS), response
        );
    }

    public void addCookie(String name, String value, int maxAge, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(cookieProperties.httpOnly());
        cookie.setSecure(cookieProperties.secure());
        cookie.setAttribute("SameSite", cookieProperties.sameSite());
        response.addCookie(cookie);
    }


    public void invalidateCookie(String name, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(cookieProperties.httpOnly());
        cookie.setSecure(cookieProperties.secure());
        cookie.setAttribute("SameSite", cookieProperties.sameSite());

        response.addCookie(cookie);
    }

}
