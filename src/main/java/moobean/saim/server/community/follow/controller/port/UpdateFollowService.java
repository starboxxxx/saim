package moobean.saim.server.community.follow.controller.port;

import moobean.saim.server.community.follow.controller.response.FollowListResponse;
import moobean.saim.server.community.follow.domain.Follow;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.FollowErrorCode;
import moobean.saim.server.user.domain.User;

import java.util.List;

public interface UpdateFollowService {
    void doFollow(Long followerId, Long targetUserId);

    void dropFollower(Long userId, Long targetUserId);

    void dropFollowing(Long userId, Long targetUserId);
}
