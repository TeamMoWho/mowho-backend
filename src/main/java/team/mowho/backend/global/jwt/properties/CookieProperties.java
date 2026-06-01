package team.mowho.backend.global.jwt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cookie")
public record CookieProperties(

        String domain,

        boolean httpOnly,

        boolean secure,

        String sameSite

) {
}
