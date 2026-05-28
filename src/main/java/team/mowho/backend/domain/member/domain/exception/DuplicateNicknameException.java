package team.mowho.backend.domain.member.domain.exception;

import team.mowho.backend.global.exception.CustomException;

public class DuplicateNicknameException extends CustomException {

    public DuplicateNicknameException() {
        super(MemberExceptionCode.DUPLICATE_NICKNAME);
    }

}
