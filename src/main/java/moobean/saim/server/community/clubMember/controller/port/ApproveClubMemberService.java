package moobean.saim.server.community.clubMember.controller.port;

import moobean.saim.server.community.clubMember.controller.request.ApproveClubMemberRequest;

public interface ApproveClubMemberService {

    void approveClubMember(Long userId, ApproveClubMemberRequest request);
}
