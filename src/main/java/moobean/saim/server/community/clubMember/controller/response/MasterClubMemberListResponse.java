package moobean.saim.server.community.clubMember.controller.response;

import moobean.saim.server.community.clubMember.infrastructure.entity.ClubRole;

public record MasterClubMemberListResponse(
        Long userId,
        String userName,
        String email,
        ClubRole clubRole
) {
}
