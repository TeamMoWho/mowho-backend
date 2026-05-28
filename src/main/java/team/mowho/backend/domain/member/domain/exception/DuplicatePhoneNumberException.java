package team.mowho.backend.domain.member.domain.exception;

import team.mowho.backend.global.exception.CustomException;

public class DuplicatePhoneNumberException extends CustomException {

    public DuplicatePhoneNumberException() {
        super(MemberExceptionCode.DUPLICATE_PHONE_NUMBER);
    }

}
