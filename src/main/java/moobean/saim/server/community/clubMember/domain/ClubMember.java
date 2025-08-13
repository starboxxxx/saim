package moobean.saim.server.community.clubMember.domain;

import lombok.Builder;
import lombok.Getter;
import moobean.saim.server.community.club.domain.Club;
import moobean.saim.server.community.clubMember.infrastructure.entity.ClubMemberStatus;
import moobean.saim.server.community.clubMember.infrastructure.entity.ClubRole;
import moobean.saim.server.user.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ClubMember {

    private Long id;

    private Club club;

    private User user;

    private ClubRole clubRole;

    private ClubMemberStatus status;

    private LocalDate joinDate;

    private LocalDateTime createdTime;

    @Builder
    public ClubMember(Long id, ClubRole clubRole, User user, Club club,
                      ClubMemberStatus status, LocalDate joinDate, LocalDateTime createdTime) {
        this.id = id;
        this.club = club;
        this.user = user;
        this.clubRole = clubRole;
        this.status = status;
        this.joinDate = joinDate;
        this.createdTime = createdTime;
    }

    public static ClubMember createMaster(Club club, User user) {
        return ClubMember.builder()
                .club(club)
                .user(user)
                .clubRole(ClubRole.MASTER)
                .status(ClubMemberStatus.APPROVED)
                .joinDate(LocalDate.now())
                .build();
    }

    public static ClubMember createMember(Club club, User user,
                                          ClubMemberStatus status, LocalDate joinDate) {
        return ClubMember.builder()
                .club(club)
                .user(user)
                .clubRole(ClubRole.GENERAL)
                .status(status)
                .joinDate(joinDate)
                .build();
    }

    public void approveClubMember() {
        this.status = ClubMemberStatus.APPROVED;
        this.joinDate = LocalDate.now();
    }

    public void updateClubRole(ClubRole role) {
        this.clubRole = role;
    }
}
