package moobean.saim.server.community.club.domain;

import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import moobean.saim.server.community.club.controller.request.CreateClubRequest;
import moobean.saim.server.community.club.controller.request.UpdateClubRequest;
import moobean.saim.server.user.domain.User;

import java.time.LocalDateTime;

@Getter
public class Club {

    private Long id;

    private String name;

    private String introduce;

    private Boolean showClub;

    private Long memberCount;

    private Long postCount;

    private LocalDateTime createdTime;


    @Builder
    public Club(Long id, String name,
                String introduce, Boolean showClub,
                Long memberCount, Long postCount,
                LocalDateTime createdTime) {
        this.id = id;
        this.name = name;
        this.introduce = introduce;
        this.showClub = showClub;
        this.memberCount = memberCount;
        this.postCount = postCount;
        this.createdTime = createdTime;
    }

    public static Club create(CreateClubRequest request) {
        return Club.builder()
                .name(request.name())
                .introduce(request.introduce())
                .showClub(request.showClub())
                .memberCount(1L)
                .postCount(0L)
                .build();
    }

    public void update(UpdateClubRequest request) {
        this.name = request.name();
        this.introduce = request.introduce();
        this.showClub = request.showClub();
    }

    public void plusMemberCount() {
        this.memberCount += 1;
    }

    public void minusMemberCount() {
        this.memberCount -= 1;
    }
}
