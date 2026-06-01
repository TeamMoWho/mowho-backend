package team.mowho.backend.global.jwt.properties;

import jakarta.validation.constraints.Pattern;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "cookie")
public record CookieProperties(

        String domain,

        boolean httpOnly,

        boolean secure,

        @Pattern(
                regexp = "^(Strict|Lax|None)$",
                message = "sameSite는 Strict, Lax, None 중 하나여야 합니다."
        )
        String sameSite

) {
}

