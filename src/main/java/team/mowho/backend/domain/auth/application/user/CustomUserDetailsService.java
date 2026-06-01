package team.mowho.backend.domain.auth.application.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.mowho.backend.domain.auth.domain.user.CustomUserDetails;
import team.mowho.backend.domain.member.domain.Member;
import team.mowho.backend.domain.member.domain.MemberRepository;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Long memberId = Long.valueOf(username);
        Member member = memberRepository.findByMemberId(memberId);

        return new CustomUserDetails(member.getId(), member.getMemberRole());
    }

}
