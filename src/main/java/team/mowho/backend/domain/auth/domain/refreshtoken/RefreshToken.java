package team.mowho.backend.domain.auth.domain.refreshtoken;

import jakarta.persistence.*;
import lombok.*;
import team.mowho.backend.domain.auth.domain.exception.RefreshTokenExpiredException;
import team.mowho.backend.domain.auth.domain.exception.RefreshTokenNotValidException;
import team.mowho.backend.domain.auth.domain.exception.TokenHashFailedException;
import team.mowho.backend.global.domain.BaseTimeEntity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HexFormat;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(
        name = "refresh_tokens",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_refresh_token_member_id", columnNames = "member_id")
        }
)
public class RefreshToken extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String tokenHash;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    public static RefreshToken of(Long memberId, String token, long expiredSeconds) {
        return RefreshToken.builder()
                .memberId(memberId)
                .tokenHash(hash(token))
                .expiredAt(LocalDateTime.now().plusSeconds(expiredSeconds))
                .build();
    }

    public void rotate(String newToken, long expiredSeconds) {
        this.tokenHash = hash(newToken);
        this.expiredAt = LocalDateTime.now().plusSeconds(expiredSeconds);
    }

    public void validate(String token, Long memberId) {
        if (!this.memberId.equals(memberId) || !this.tokenHash.equals(hash(token))) {
            throw new RefreshTokenNotValidException();
        }
        if (LocalDateTime.now().isAfter(this.expiredAt)) {
            throw new RefreshTokenExpiredException();
        }
    }

    private static String hash(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new TokenHashFailedException(e);
        }
    }

}
