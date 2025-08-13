package moobean.saim.server.community.follow.domain;

import lombok.Builder;
import lombok.Getter;
import moobean.saim.server.user.domain.User;

@Getter
public class Follow {

    private final Long id;

    private final User follower;

    private final User targetUser;

    @Builder
    public Follow(Long id, User follower, User targetUser) {
        this.id = id;
        this.follower = follower;
        this.targetUser = targetUser;
    }

    public static Follow create(User follower, User targetUser) {
        return Follow.builder()
                .follower(follower)
                .targetUser(targetUser)
                .build();
    }
}
