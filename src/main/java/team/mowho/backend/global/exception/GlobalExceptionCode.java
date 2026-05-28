package team.mowho.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum GlobalExceptionCode implements ExceptionCode {
    INVALID_INPUT(BAD_REQUEST, "유효한 입력 형식이 아닙니다.", LogLevel.INFO),
    INVALID_JSON(BAD_REQUEST, "올바르지 않은 JSON 형식입니다.", LogLevel.INFO),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메서드입니다.", LogLevel.INFO),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "지원하지 않는 미디어 타입입니다.", LogLevel.INFO),
    SERVER_ERROR(INTERNAL_SERVER_ERROR, "예상치 못한 문제가 발생했습니다.", LogLevel.ERROR)
    ;

    private final HttpStatus status;

    private final String message;

    private final LogLevel logLevel;

    @Override
    public String getCode() {
        return this.name();
    }

}
