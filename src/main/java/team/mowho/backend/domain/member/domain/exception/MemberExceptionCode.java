package team.mowho.backend.domain.member.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import team.mowho.backend.global.exception.ExceptionCode;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum MemberExceptionCode implements ExceptionCode {
    MEMBER_NOT_FOUND(NOT_FOUND, "존재하지 않는 회원입니다.", LogLevel.INFO),
    PASSWORD_NOT_MATCH(BAD_REQUEST, "비밀번호가 일치하지 않습니다.", LogLevel.INFO),
    DUPLICATE_LOGIN_ID(CONFLICT, "이미 사용 중인 아이디입니다.", LogLevel.INFO),
    DUPLICATE_NICKNAME(CONFLICT, "이미 사용 중인 닉네임입니다.", LogLevel.INFO),
    DUPLICATE_PHONE_NUMBER(CONFLICT, "이미 사용 중인 전화번호입니다.", LogLevel.INFO),
    DUPLICATE_MEMBER(CONFLICT, "이미 가입된 회원입니다.", LogLevel.INFO),
    ;

    private final HttpStatus status;

    private final String message;

    private final LogLevel logLevel;

    @Override
    public String getCode() {
        return this.name();
    }

}
