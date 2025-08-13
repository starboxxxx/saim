package moobean.saim.server.community.clubMember.controller.port;

import moobean.saim.server.community.clubMember.controller.request.DropClubMemberRequest;

public interface DropClubMemberService {

    void dropClubMember(Long userId, DropClubMemberRequest request);
}
