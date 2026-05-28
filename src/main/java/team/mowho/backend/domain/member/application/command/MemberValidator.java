package team.mowho.backend.domain.member.application.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.mowho.backend.domain.member.application.dto.request.MemberRegisterServiceRequest;
import team.mowho.backend.domain.member.domain.MemberRepository;
import team.mowho.backend.domain.member.domain.exception.DuplicateLoginIdException;
import team.mowho.backend.domain.member.domain.exception.DuplicateNicknameException;
import team.mowho.backend.domain.member.domain.exception.DuplicatePhoneNumberException;
import team.mowho.backend.domain.member.domain.exception.PasswordNotMatchException;

@RequiredArgsConstructor
@Component
public class MemberValidator {

    private final MemberRepository memberRepository;

    public void validate(MemberRegisterServiceRequest request) {
        validatePasswordMatch(request.password(), request.passwordConfirm());
        validateDuplicateLoginId(request.loginId());
        validateDuplicateNickname(request.nickname());
        validateDuplicatePhoneNumber(request.phoneNumber());
    }

    private void validatePasswordMatch(String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            throw new PasswordNotMatchException();
        }
    }

    private void validateDuplicateLoginId(String loginId) {
        if (memberRepository.existsByLoginId(loginId)) {
            throw new DuplicateLoginIdException();
        }
    }

    private void validateDuplicateNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException();
        }
    }

    private void validateDuplicatePhoneNumber(String phoneNumber) {
        if (memberRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DuplicatePhoneNumberException();
        }
    }

}
