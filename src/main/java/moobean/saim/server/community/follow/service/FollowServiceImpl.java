package moobean.saim.server.community.follow.service;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.follow.controller.port.FindFollowService;
import moobean.saim.server.community.follow.controller.port.UpdateFollowService;
import moobean.saim.server.community.follow.controller.response.FollowListResponse;
import moobean.saim.server.community.follow.domain.Follow;
import moobean.saim.server.community.follow.domain.mapper.FollowMapper;
import moobean.saim.server.community.follow.service.port.FollowRepository;
import moobean.saim.server.community.profile.domain.Profile;
import moobean.saim.server.community.profile.service.domain.ProfileService;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.FollowErrorCode;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FindFollowService, UpdateFollowService {

    private final FollowRepository followRepository;
    private final UserService userService;
    private final ProfileService profileService;

    @Override
    public void doFollow(Long followerId, Long targetUserId) {

        if (followerId.equals(targetUserId)) {
            throw new ApplicationException(FollowErrorCode.FOLLOWER_TARGET_EQUAL);
        }

        User follower = userService.findUserInFollowService(followerId, FollowErrorCode.FOLLOWER_NOT_FOUND);

        User targetUser = userService.findUserInFollowService(targetUserId, FollowErrorCode.TARGET_USER_NOT_FOUND);

        Profile followerProfile = follower.getProfile();
        Profile targetProfile = targetUser.getProfile();

        plusFollow(followerProfile, targetProfile);

        Follow follow = Follow.create(follower, targetUser);
        followRepository.follow(follow);
    }

    @Override
    public void dropFollower(Long userId, Long targetUserId) {

        User follower = userService.findUserInFollowService(targetUserId, FollowErrorCode.FOLLOWER_NOT_FOUND);

        User targetUser = userService.findUserInFollowService(userId, FollowErrorCode.TARGET_USER_NOT_FOUND);

        minusFollow(follower.getProfile(), targetUser.getProfile());

        Follow follow = followRepository.find(targetUserId, userId);

        followRepository.dropFollow(follow);

    }

    @Override
    public void dropFollowing(Long userId, Long targetUserId) {
        User follower = userService.findUserInFollowService(userId, FollowErrorCode.FOLLOWER_NOT_FOUND);

        User targetUser = userService.findUserInFollowService(targetUserId, FollowErrorCode.TARGET_USER_NOT_FOUND);

        minusFollow(follower.getProfile(), targetUser.getProfile());

        Follow follow = followRepository.find(userId, targetUserId);

        followRepository.dropFollow(follow);
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

    private void plusFollow(Profile followerProfile, Profile targetProfile) {
        followerProfile.plusFollowingNum();
        targetProfile.plusFollowerNum();

        profileService.save(followerProfile);
        profileService.save(targetProfile);
    }

    private void minusFollow(Profile followerProfile, Profile targetProfile) {
        followerProfile.minusFollowerNum();
        targetProfile.minusFollowerNum();

        profileService.save(followerProfile);
        profileService.save(targetProfile);
    }

}
