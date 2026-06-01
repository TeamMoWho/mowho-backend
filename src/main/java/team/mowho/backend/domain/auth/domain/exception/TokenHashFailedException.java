package team.mowho.backend.domain.auth.domain.exception;

import team.mowho.backend.global.exception.CustomException;

public class TokenHashFailedException extends CustomException {

    public TokenHashFailedException(Throwable cause) {
        super(AuthExceptionCode.TOKEN_HASH_FAILED, cause);
    }

}

