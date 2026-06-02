package team.mowho.backend.domain.auth.domain.exception;

import team.mowho.backend.global.exception.CustomException;

public class RefreshTokenExpiredException extends CustomException {

    public RefreshTokenExpiredException() {
        super(AuthExceptionCode.REFRESH_TOKEN_EXPIRED);
    }

}

