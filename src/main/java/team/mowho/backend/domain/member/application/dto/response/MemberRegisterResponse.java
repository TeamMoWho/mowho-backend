package team.mowho.backend.domain.member.application.dto.response;

import lombok.Builder;

@Builder
public record MemberRegisterResponse(

        Long memberId

) {
}
