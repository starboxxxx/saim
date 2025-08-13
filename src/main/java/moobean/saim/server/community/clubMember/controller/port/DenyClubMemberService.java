package moobean.saim.server.community.clubMember.controller.port;

import moobean.saim.server.community.clubMember.controller.request.DenyClubMemberRequest;

public interface DenyClubMemberService {
    void denyClubMember(Long userId, DenyClubMemberRequest clubMember);
}
