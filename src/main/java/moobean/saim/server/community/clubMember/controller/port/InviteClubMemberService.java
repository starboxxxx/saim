package moobean.saim.server.community.clubMember.controller.port;

import moobean.saim.server.community.clubMember.controller.request.InviteClubMemberRequest;

public interface InviteClubMemberService {

    void inviteMember(Long userId, InviteClubMemberRequest request);
}
