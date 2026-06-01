package team.mowho.backend.domain.auth.domain.exception;

import team.mowho.backend.global.exception.CustomException;

public class RefreshTokenNotValidException extends CustomException {

    public RefreshTokenNotValidException(Throwable cause) {
        super(AuthExceptionCode.REFRESH_TOKEN_NOT_VALID, cause);
    }

}

