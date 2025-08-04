package moobean.saim.server.community.service;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.controller.port.FollowService;
import moobean.saim.server.community.controller.response.FollowListResponse;
import moobean.saim.server.community.domain.Follow;
import moobean.saim.server.community.service.mapper.FollowMapper;
import moobean.saim.server.community.service.port.FollowRepository;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.FollowErrorCode;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.service.port.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Override
    public Boolean checkFollow(Long followerId, Long targetUserId) {
        return followRepository.exist(followerId, targetUserId);
    }

    @Override
    public void doFollow(Long followerId, Long targetUserId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new ApplicationException(FollowErrorCode.FOLLOWER_NOT_FOUND));

        User targetUser = userRepository.findById(targetUserId)
                .orElseThrow(() -> new ApplicationException(FollowErrorCode.TARGET_USER_NOT_FOUND));

        Follow follow = Follow.create(follower, targetUser);
        followRepository.follow(follow);
    }

    @Override
    public void unfollow(Long followerId, Long targetUserId) {
        Follow follow = followRepository.find(followerId, targetUserId);
        followRepository.unfollow(follow);
    }

    @Override
    public List<FollowListResponse> getFollowers(Long targetUserId) {
        return followRepository.getFollowers(targetUserId)
                .stream()
                .map(FollowMapper::toFollowerListResponse)
                .toList();
    }

    @Override
    public List<FollowListResponse> getFollowings(Long followerId) {
        return followRepository.getFollowings(followerId)
                .stream()
                .map(FollowMapper::toFollowingListResponse)
                .toList();
    }
}
