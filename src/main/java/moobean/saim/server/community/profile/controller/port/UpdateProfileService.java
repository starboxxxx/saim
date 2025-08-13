package moobean.saim.server.community.profile.controller.port;

import moobean.saim.server.community.profile.controller.request.UpdateProfileRequest;

public interface UpdateProfileService {

    void updateProfile(Long userId, UpdateProfileRequest request);
}
