package team.mowho.backend.domain.auth.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import team.mowho.backend.global.exception.ExceptionCode;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Getter
@RequiredArgsConstructor
public enum AuthExceptionCode implements ExceptionCode {
    REFRESH_TOKEN_NOT_FOUND(UNAUTHORIZED, "리프레시 토큰이 존재하지 않습니다.", LogLevel.INFO),
    REFRESH_TOKEN_NOT_VALID(UNAUTHORIZED, "유효하지 않은 리프레시 토큰입니다.", LogLevel.INFO),
    REFRESH_TOKEN_EXPIRED(UNAUTHORIZED, "만료된 리프레시 토큰입니다.", LogLevel.INFO),
    TOKEN_HASH_FAILED(INTERNAL_SERVER_ERROR, "토큰 해싱 중 오류가 발생했습니다.", LogLevel.ERROR),
    LOGIN_FAILED(UNAUTHORIZED, "아이디 또는 비밀번호가 올바르지 않습니다.", LogLevel.INFO)
    ;

    private final HttpStatus status;

    private final String message;

    private final LogLevel logLevel;

    @Override
    public String getCode() {
        return this.name();
    }

}
