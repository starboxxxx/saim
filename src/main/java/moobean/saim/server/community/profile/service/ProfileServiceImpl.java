package moobean.saim.server.community.profile.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moobean.saim.server.community.follow.service.domain.FollowService;
import moobean.saim.server.community.profile.controller.port.FindProfileService;
import moobean.saim.server.community.profile.controller.port.UpdateProfileService;
import moobean.saim.server.community.profile.controller.request.UpdateProfileRequest;
import moobean.saim.server.community.profile.controller.response.MyPageProfileResponse;
import moobean.saim.server.community.profile.controller.response.ProfileResponse;
import moobean.saim.server.community.profile.controller.response.TrainerResponse;
import moobean.saim.server.community.profile.domain.Profile;
import moobean.saim.server.community.profile.domain.mapper.ProfileResponseMapper;
import moobean.saim.server.community.profile.service.port.ProfileRepository;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.ProfileErrorCode;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.service.domain.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements FindProfileService, UpdateProfileService {

    private final ProfileRepository profileRepository;
    private final UserService userService;
    private final FollowService followService;

    @Override
    public ProfileResponse getProfile(Long targetUserId, Long userId) {

        Boolean isSame = targetUserId.equals(userId);
        Boolean isFollowed = false;

        if (!isSame) {
            isFollowed = followService.checkFollow(userId, targetUserId);
        }

        Profile profile = profileRepository.findByUserId(targetUserId)
                .orElseThrow(() -> new ApplicationException(ProfileErrorCode.PROFILE_NOT_FOUND));

        return ProfileResponseMapper.toProfileResponse(profile, isSame, isFollowed);
    }

    @Override
    public MyPageProfileResponse getMyPageProfile(Long userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ApplicationException(ProfileErrorCode.PROFILE_NOT_FOUND));

        User user = userService.find(userId);
        return ProfileResponseMapper.toMyPageProfileResponse(profile, user);
    }

    @Override
    public TrainerResponse getTrainer(Long userId) {
        User user = userService.find(userId);

        return ProfileResponseMapper.toTrainerResponse(user);
    }

    @Transactional
    @Override
    public void updateProfile(Long userId, UpdateProfileRequest request) {

        User user = userService.find(userId);

        Profile profile = user.getProfile();

        profile.updateIntroduce(request.introduce());
        user.updateNameAndEmail(request.name(), request.email());

        user.updateProfile(profile);
        userService.updateUser(user);
    }
}
