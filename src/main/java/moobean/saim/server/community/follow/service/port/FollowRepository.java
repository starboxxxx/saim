package moobean.saim.server.community.follow.service.port;

import moobean.saim.server.community.follow.domain.Follow;

import java.util.List;

public interface FollowRepository {

    Follow find(Long followerId, Long targetUserId);

    Boolean exist(Long followerId, Long targetUserId);

    void follow(Follow follow);

    void dropFollow(Follow follow);

    List<Follow> getFollowers(Long targetUserId);

    List<Follow> getFollowings(Long followerId);
}
