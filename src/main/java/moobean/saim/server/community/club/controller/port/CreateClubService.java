package moobean.saim.server.community.club.controller.port;

import moobean.saim.server.community.club.controller.request.CreateClubRequest;

public interface CreateClubService {

    void createClub(Long userId, CreateClubRequest request);
}
