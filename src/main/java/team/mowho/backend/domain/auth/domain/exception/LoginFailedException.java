package team.mowho.backend.domain.auth.domain.exception;

import team.mowho.backend.global.exception.CustomException;

public class LoginFailedException extends CustomException {

    public LoginFailedException() {
        super(AuthExceptionCode.LOGIN_FAILED);
    }

    public LoginFailedException(Throwable cause) {
        super(AuthExceptionCode.LOGIN_FAILED, cause);
    }

}

