package moobean.saim.server.community.follow.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import moobean.saim.server.community.follow.domain.Follow;
import moobean.saim.server.global.BaseTimeEntity;
import moobean.saim.server.user.infrastructure.entity.UserEntity;

@Entity
@Table(name = "FOLLOW")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private UserEntity follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_user_id", nullable = false)
    private UserEntity targetUser;

    public static FollowEntity from(Follow follow) {
        FollowEntity followEntity = new FollowEntity();
        followEntity.id = follow.getId();
        followEntity.follower = UserEntity.from(follow.getFollower());
        followEntity.targetUser = UserEntity.from(follow.getTargetUser());
        return followEntity;
    }

    public Follow toModel() {
        return Follow.builder()
                .id(id)
                .follower(follower.toModel())
                .targetUser(targetUser.toModel())
                .build();
    }
}
