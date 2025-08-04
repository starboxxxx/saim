package moobean.saim.server.community.service.port;

import moobean.saim.server.community.domain.Follow;
import moobean.saim.server.user.domain.User;

import java.util.List;

public interface FollowRepository {

    Follow find(Long followerId, Long targetUserId);

    Boolean exist(Long followerId, Long targetUserId);

    void follow(Follow follow);

    void unfollow(Follow follow);

    List<Follow> getFollowers(Long targetUserId);

    List<Follow> getFollowings(Long followerId);
}
