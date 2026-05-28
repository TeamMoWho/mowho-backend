package team.mowho.backend.domain.member.presentation.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import team.mowho.backend.domain.member.application.dto.request.MemberRegisterServiceRequest;

@Builder
public record MemberRegisterRequest(

        @NotBlank(message = "아이디를 입력해주세요.")
        String loginId,

        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "이메일 형식이 아닙니다.")
        String email,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        String password,

        @NotBlank(message = "비밀번호 확인을 입력해주세요.")
        String passwordConfirm,

        @NotBlank(message = "닉네임을 입력해주세요.")
        @Size(min = 2, max = 8, message = "닉네임은 2~8자여야 합니다.")
        String nickname,

        @NotBlank(message = "전화번호를 입력해주세요.")
        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 아닙니다.")
        String phoneNumber

) {

        public MemberRegisterServiceRequest toServiceRequest() {
                return MemberRegisterServiceRequest.builder()
                        .loginId(loginId)
                        .email(email)
                        .password(password)
                        .passwordConfirm(passwordConfirm)
                        .nickname(nickname)
                        .phoneNumber(phoneNumber)
                        .build();
        }

}
