package team.mowho.backend.domain.member.application.command;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mowho.backend.domain.member.application.dto.request.MemberRegisterServiceRequest;
import team.mowho.backend.domain.member.application.dto.response.MemberRegisterResponse;
import team.mowho.backend.domain.member.domain.Member;
import team.mowho.backend.domain.member.domain.MemberRepository;
import team.mowho.backend.domain.member.domain.exception.DuplicateMemberException;

@RequiredArgsConstructor
@Service
public class MemberCommandService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberRegisterResponse register(MemberRegisterServiceRequest request) {
        memberValidator.validate(request);

        Member member = Member.register(
                request.loginId(),
                request.email(),
                passwordEncoder.encode(request.password()),
                request.nickname(),
                request.phoneNumber()
        );
        try {
            memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateMemberException();
        }

        return MemberRegisterResponse.builder()
                .memberId(member.getId())
                .build();
    }

}
