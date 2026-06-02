package team.mowho.backend.global.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenType {
    ACCESS("accessToken", "access"),
    REFRESH("refreshToken", "refresh");

    private final String cookieName;

    private final String claimValue;

}


