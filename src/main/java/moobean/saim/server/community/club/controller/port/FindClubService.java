package moobean.saim.server.community.club.controller.port;

import moobean.saim.server.community.club.controller.response.ClubDetailResponse;
import moobean.saim.server.community.club.controller.response.MyClubListResponse;
import moobean.saim.server.community.club.controller.response.MyClubNameListResponse;

import java.util.List;

public interface FindClubService {

    ClubDetailResponse getClubDetail(Long userId, Long clubId);

    MyClubListResponse getMyClubList(Long userId);

    List<MyClubNameListResponse> getMyClubNameList(Long userId);
}
