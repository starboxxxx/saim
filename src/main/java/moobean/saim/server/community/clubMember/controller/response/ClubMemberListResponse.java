package moobean.saim.server.community.clubMember.controller.response;

import java.util.List;

public record ClubMemberListResponse(
        Boolean secretClub,
        List<ClubMemberResponse> clubMembers
) {
}
