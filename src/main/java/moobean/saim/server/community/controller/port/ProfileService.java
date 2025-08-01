package moobean.saim.server.community.controller.port;

import moobean.saim.server.community.controller.request.UpdateProfileRequest;
import moobean.saim.server.community.controller.response.MyPageProfileResponse;
import moobean.saim.server.community.controller.response.ProfileResponse;

public interface ProfileService {

    ProfileResponse getProfile(Long targetUserId, Long userId);

    MyPageProfileResponse getMyPageProfile(Long userId);

    void updateProfile(Long userId, UpdateProfileRequest request);
}
