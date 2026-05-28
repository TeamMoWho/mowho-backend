package team.mowho.backend.domain.member.domain;

public interface MemberRepository {

    void save(Member member);

    boolean existsByLoginId(String loginId);

    boolean existsByNickname(String nickname);

    boolean existsByPhoneNumber(String phoneNumber);

}
