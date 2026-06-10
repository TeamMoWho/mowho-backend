package team.mowho.backend.domain.membercategory.domain;

import jakarta.persistence.*;
import lombok.*;
import team.mowho.backend.global.domain.BaseTimeEntity;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(
        name = "member_categories",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_member_category", columnNames = {"member_id", "category_id"})
        }
)
public class MemberCategory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long categoryId;

    public static MemberCategory of(Long memberId, Long categoryId) {
        return MemberCategory.builder()
                .memberId(memberId)
                .categoryId(categoryId)
                .build();
    }

}
