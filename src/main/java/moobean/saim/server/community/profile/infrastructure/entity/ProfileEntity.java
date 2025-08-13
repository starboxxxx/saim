package moobean.saim.server.community.profile.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import moobean.saim.server.community.profile.domain.Profile;
import moobean.saim.server.global.BaseTimeEntity;
import moobean.saim.server.user.infrastructure.entity.UserEntity;

@Entity
@Table(name = "PROFILE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String introduce;

    @Column(nullable = false)
    private Boolean showAbility;

    @Column(nullable = false)
    private Integer postNum;

    @Column(nullable = false)
    private Integer followerNum;

    @Column(nullable = false)
    private Integer followingNum;

    @OneToOne(mappedBy = "profile")
    private UserEntity user;

    public static ProfileEntity from(Profile profile) {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.id = profile.getId();
        profileEntity.introduce = profile.getIntroduce();
        profileEntity.showAbility = profile.getShowAbility();
        profileEntity.postNum = profile.getPostNum();
        profileEntity.followerNum = profile.getFollowerNum();
        profileEntity.followingNum = profile.getFollowingNum();
        return profileEntity;
    }

    public Profile toModel() {
        return Profile.builder()
                .id(id)
                .introduce(introduce)
                .showAbility(showAbility)
                .postNum(postNum)
                .followerNum(followerNum)
                .followingNum(followingNum)
                .build();
    }
}
