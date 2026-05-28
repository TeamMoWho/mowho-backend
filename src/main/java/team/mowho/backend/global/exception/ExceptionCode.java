package team.mowho.backend.global.exception;

import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

public interface ExceptionCode {

    HttpStatus getStatus();

    String getCode();

    String getMessage();

    LogLevel getLogLevel();

}
