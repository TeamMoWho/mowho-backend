package team.mowho.backend.global.jwt.properties;

public record CookieProperties(

        String domain,

        boolean httpOnly,

        boolean secure,

        String sameSite

) {
}
