package team.mowho.backend.global.exception;

import lombok.Getter;
import org.springframework.boot.logging.LogLevel;

@Getter
public class CustomException extends RuntimeException {

    private final ExceptionCode code;
    private final LogLevel logLevel;

    public CustomException(ExceptionCode code) {
        super(code.getMessage());
        this.code = code;
        this.logLevel = code.getLogLevel();
    }

    public CustomException(ExceptionCode code, LogLevel logLevel) {
        super(code.getMessage());
        this.code = code;
        this.logLevel = logLevel;
    }

    public CustomException(ExceptionCode code, Throwable cause) {
        super(code.getMessage(), cause);
        this.code = code;
        this.logLevel = code.getLogLevel();
    }

}
