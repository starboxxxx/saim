package moobean.saim.server.community.profile.controller.port;

import moobean.saim.server.community.profile.controller.response.MyPageProfileResponse;
import moobean.saim.server.community.profile.controller.response.ProfileResponse;
import moobean.saim.server.community.profile.controller.response.TrainerResponse;

public interface FindProfileService {

    ProfileResponse getProfile(Long targetUserId, Long userId);

    MyPageProfileResponse getMyPageProfile(Long userId);

    TrainerResponse getTrainer(Long userId);
}
