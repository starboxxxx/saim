package moobean.saim.server.community.clubMember.controller.port;

import moobean.saim.server.community.clubMember.controller.response.ClubMemberListResponse;


public interface FindClubMemberService {

    ClubMemberListResponse findClubMembers(Long userId, Long clubId);
}
