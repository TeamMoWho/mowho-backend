package team.mowho.backend.domain.auth.domain.exception;

import team.mowho.backend.global.exception.CustomException;

public class RefreshTokenNotFoundException extends CustomException {

    public RefreshTokenNotFoundException() {
        super(AuthExceptionCode.REFRESH_TOKEN_NOT_FOUND);
    }

}

