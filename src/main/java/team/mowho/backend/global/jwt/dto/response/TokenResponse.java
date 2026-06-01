package team.mowho.backend.global.jwt.dto.response;

public record TokenResponse(

        String accessToken,

        String refreshToken

) {
}
