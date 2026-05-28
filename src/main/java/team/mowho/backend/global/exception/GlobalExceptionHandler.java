package team.mowho.backend.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;
import java.util.stream.Collectors;

import static net.logstash.logback.argument.StructuredArguments.keyValue;
import static team.mowho.backend.global.exception.GlobalExceptionCode.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ExceptionResponse> handleCustomException(CustomException exception) {

        switch (exception.getLogLevel()) {
            case ERROR -> log.error("[ERROR]",
                    keyValue("code", exception.getCode().getCode()),
                    keyValue("errorMessage", exception.getMessage()),
                    exception);
            case WARN -> log.warn("[WARN]",
                    keyValue("code", exception.getCode().getCode()),
                    keyValue("errorMessage", exception.getMessage()));
            default -> log.info("[INFO]",
                    keyValue("code", exception.getCode().getCode()),
                    keyValue("errorMessage", exception.getMessage()));
        }

        return ResponseEntity
                .status(exception.getCode().getStatus())
                .body(ExceptionResponse.from(exception));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        log.error("[ERROR]",
                keyValue("errorClass", exception.getClass().getSimpleName()),
                keyValue("errorMessage", exception.getMessage()),
                exception);
        return ResponseEntity.internalServerError().body(ExceptionResponse.from(SERVER_ERROR));
    }

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(
            HandlerMethodValidationException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        String message = exception.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .status(INVALID_INPUT.getStatus())
                .body(ExceptionResponse.from(INVALID_INPUT, message));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        String message = exception.getFieldErrors().stream()
                .filter(error -> error.getDefaultMessage() != null)
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .status(INVALID_INPUT.getStatus())
                .body(ExceptionResponse.from(INVALID_INPUT, message));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        return ResponseEntity
                .status(INVALID_JSON.getStatus())
                .body(ExceptionResponse.from(INVALID_JSON));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        return ResponseEntity
                .status(METHOD_NOT_ALLOWED.getStatus())
                .body(ExceptionResponse.from(METHOD_NOT_ALLOWED));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        return ResponseEntity
                .status(UNSUPPORTED_MEDIA_TYPE.getStatus())
                .body(ExceptionResponse.from(UNSUPPORTED_MEDIA_TYPE));
    }

}
