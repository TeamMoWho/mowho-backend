package team.mowho.backend.global.exception;

import lombok.Builder;

@Builder
public record ExceptionResponse(
        String code,
        String message
) {

    public static ExceptionResponse from(CustomException exception) {
        return ExceptionResponse.builder()
                .code(exception.getCode().getCode())
                .message(exception.getMessage())
                .build();
    }

    public static ExceptionResponse from(ExceptionCode code) {
        return ExceptionResponse.builder()
                .code(code.getCode())
                .message(code.getMessage())
                .build();
    }

    public static ExceptionResponse from(ExceptionCode code, String message) {
        return ExceptionResponse.builder()
                .code(code.getCode())
                .message(message)
                .build();
    }

}
