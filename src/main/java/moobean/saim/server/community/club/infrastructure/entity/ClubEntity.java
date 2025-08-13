package moobean.saim.server.community.club.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import moobean.saim.server.community.board.article.infrastructure.entity.ArticleEntity;
import moobean.saim.server.community.club.domain.Club;
import moobean.saim.server.community.clubMember.infrastructure.entity.ClubMemberEntity;
import moobean.saim.server.community.follow.infrastructure.entity.FollowEntity;
import moobean.saim.server.global.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLUB")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String introduce;

    @Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long memberCount;

    @Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long postCount;

    @Column(nullable = false)
    private Boolean showClub;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<ArticleEntity> articleList = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<ClubMemberEntity> clubMemberList = new ArrayList<>();

    public static ClubEntity from(Club club) {
        ClubEntity entity = new ClubEntity();
        entity.id = club.getId();
        entity.name = club.getName();
        entity.introduce = club.getIntroduce();
        entity.memberCount = club.getMemberCount();
        entity.postCount = club.getPostCount();
        entity.showClub = club.getShowClub();

        return entity;
    }

    public Club toModel() {
        return Club.builder()
                .id(id)
                .name(name)
                .introduce(introduce)
                .memberCount(memberCount)
                .postCount(postCount)
                .showClub(showClub)
                .createdTime(getCreatedTime())
                .build();
    }
}
