package moobean.saim.server.community.clubMember.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import moobean.saim.server.community.clubMember.domain.ClubMember;
import moobean.saim.server.community.club.infrastructure.entity.ClubEntity;
import moobean.saim.server.global.BaseTimeEntity;
import moobean.saim.server.user.infrastructure.entity.UserEntity;

import java.time.LocalDate;

@Entity
@Table(name = "CLUB_MEMBER")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubMemberEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // N:1 관계
    @JoinColumn(name = "club_id", nullable = false)
    private ClubEntity club;

    @ManyToOne(fetch = FetchType.LAZY) // N:1 관계
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private ClubRole clubRole;

    @Enumerated(EnumType.STRING)
    private ClubMemberStatus status;

    private LocalDate joinDate;

    public static ClubMemberEntity from(ClubMember clubMember) {
        ClubMemberEntity clubMemberEntity = new ClubMemberEntity();
        clubMemberEntity.id = clubMember.getId();
        clubMemberEntity.club = ClubEntity.from(clubMember.getClub());
        clubMemberEntity.user = UserEntity.from(clubMember.getUser());
        clubMemberEntity.clubRole = clubMember.getClubRole();
        clubMemberEntity.status = clubMember.getStatus();
        clubMemberEntity.joinDate = clubMember.getJoinDate();

        return clubMemberEntity;
    }

    public ClubMember toModel() {
        return ClubMember.builder()
                .id(id)
                .club(club.toModel())
                .user(user.toModel())
                .clubRole(clubRole)
                .status(status)
                .joinDate(joinDate)
                .createdTime(getCreatedTime())
                .build();
    }
}
