package team.mowho.backend.domain.auth.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import team.mowho.backend.domain.auth.application.dto.request.LoginServiceRequest;

@Builder
public record LoginRequest(

        @NotBlank(message = "아이디를 입력해주세요.")
        String loginId,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        String password

) {

        public LoginServiceRequest toServiceRequest() {
                return LoginServiceRequest.builder()
                        .loginId(loginId)
                        .password(password)
                        .build();
        }

}
