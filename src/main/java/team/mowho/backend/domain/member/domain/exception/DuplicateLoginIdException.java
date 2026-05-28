package team.mowho.backend.domain.member.domain.exception;

import team.mowho.backend.global.exception.CustomException;

public class DuplicateLoginIdException extends CustomException {

    public DuplicateLoginIdException() {
        super(MemberExceptionCode.DUPLICATE_LOGIN_ID);
    }

}
