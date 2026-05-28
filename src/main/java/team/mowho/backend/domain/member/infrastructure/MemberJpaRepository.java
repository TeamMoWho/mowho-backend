package team.mowho.backend.domain.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mowho.backend.domain.member.domain.Member;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    boolean existsByLoginId(String loginId);

    boolean existsByNickname(String nickname);

    boolean existsByPhoneNumber(String phoneNumber);

}
