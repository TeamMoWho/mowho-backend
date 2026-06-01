package team.mowho.backend.domain.auth.application.dto.request;

import lombok.Builder;

@Builder
public record LoginServiceRequest(

        String loginId,

        String password

) {
}
