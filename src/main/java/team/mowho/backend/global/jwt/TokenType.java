package team.mowho.backend.global.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenType {
    ACCESS("accessToken"),
    REFRESH("refreshToken");

    private final String cookieName;

}

