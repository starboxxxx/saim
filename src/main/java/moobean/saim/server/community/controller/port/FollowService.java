package moobean.saim.server.community.controller.port;

import moobean.saim.server.community.controller.response.FollowListResponse;

import java.util.List;

public interface FollowService {
    Boolean checkFollow(Long followerId, Long targetUserId);

    void doFollow(Long followerId, Long targetUserId);

    void unfollow(Long followerId, Long targetUserId);

    List<FollowListResponse> getFollowers(Long targetUserId);

    List<FollowListResponse> getFollowings(Long followerId);
}
