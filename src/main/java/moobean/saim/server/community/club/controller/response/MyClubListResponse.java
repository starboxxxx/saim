package moobean.saim.server.community.club.controller.response;

import java.util.List;

public record MyClubListResponse(
        List<ClubListResponse> masterClubList,
        List<ClubListResponse> memberClubList
) {
}
