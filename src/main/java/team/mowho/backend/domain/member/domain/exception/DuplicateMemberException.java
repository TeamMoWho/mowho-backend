package team.mowho.backend.domain.member.domain.exception;

import team.mowho.backend.global.exception.CustomException;

public class DuplicateMemberException extends CustomException {

    public DuplicateMemberException() {
        super(MemberExceptionCode.DUPLICATE_MEMBER);
    }

}

