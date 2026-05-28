package team.mowho.backend.domain.member.application.dto.request;

import lombok.Builder;

@Builder
public record MemberRegisterServiceRequest(

        String loginId,

        String email,

        String password,

        String passwordConfirm,

        String nickname,

        String phoneNumber

) {
}
