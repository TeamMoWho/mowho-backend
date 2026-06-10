package team.mowho.backend.domain.category.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import team.mowho.backend.global.exception.ExceptionCode;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum CategoryExceptionCode implements ExceptionCode {
    CATEGORY_NOT_FOUND(NOT_FOUND, "존재하지 않는 카테고리입니다.", LogLevel.INFO);

    private final HttpStatus status;

    private final String message;

    private final LogLevel logLevel;

    @Override
    public String getCode() {
        return this.name();
    }

}
