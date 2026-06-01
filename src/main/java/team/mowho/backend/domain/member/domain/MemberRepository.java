package team.mowho.backend.domain.member.domain;

import java.util.Optional;

public interface MemberRepository {

    void save(Member member);

    boolean existsByLoginId(String loginId);

    boolean existsByNickname(String nickname);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Member> findByLoginId(String loginId);

    Member findByMemberId(Long id);

}
