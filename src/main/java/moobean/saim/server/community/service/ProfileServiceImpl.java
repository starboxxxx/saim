package moobean.saim.server.community.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moobean.saim.server.community.controller.port.FollowService;
import moobean.saim.server.community.controller.port.ProfileService;
import moobean.saim.server.community.controller.request.UpdateProfileRequest;
import moobean.saim.server.community.controller.response.MyPageProfileResponse;
import moobean.saim.server.community.controller.response.ProfileResponse;
import moobean.saim.server.community.domain.Profile;
import moobean.saim.server.community.service.mapper.ProfileResponseMapper;
import moobean.saim.server.community.service.port.ProfileRepository;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.ProfileErrorCode;
import moobean.saim.server.global.exception.code.UserErrorCode;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.service.port.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
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

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(UserErrorCode.USER_NOT_FOUND));
        return ProfileResponseMapper.toMyPageProfileResponse(profile, user);
    }

    @Transactional
    @Override
    public void updateProfile(Long userId, UpdateProfileRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(UserErrorCode.USER_NOT_FOUND));

        Profile profile = user.getProfile();

        profile.updateIntroduce(request.introduce());
        user.updateNameAndEmail(request.name(), request.email());

        user.updateProfile(profile);
        userRepository.save(user);
    }
}
