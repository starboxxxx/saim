package moobean.saim.server.community.club.controller.port;

import moobean.saim.server.community.club.controller.request.UpdateClubRequest;

public interface UpdateClubService {

    void updateClub(Long userId, Long clubId, UpdateClubRequest request);
}
