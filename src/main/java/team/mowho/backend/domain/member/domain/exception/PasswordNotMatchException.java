package team.mowho.backend.domain.member.domain.exception;

import team.mowho.backend.global.exception.CustomException;

public class PasswordNotMatchException extends CustomException {

    public PasswordNotMatchException() {
        super(MemberExceptionCode.PASSWORD_NOT_MATCH);
    }

}
