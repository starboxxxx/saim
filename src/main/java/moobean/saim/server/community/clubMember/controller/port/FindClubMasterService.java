package moobean.saim.server.community.clubMember.controller.port;

import moobean.saim.server.community.clubMember.controller.response.InviteMemberListResponse;
import moobean.saim.server.community.clubMember.controller.response.MasterClubMemberListResponse;
import moobean.saim.server.community.clubMember.controller.response.PendingMemberListResponse;

import java.util.List;

public interface FindClubMasterService {

    List<PendingMemberListResponse> findPendingMemberList(Long userId, Long clubId);

    List<InviteMemberListResponse> findInviteMemberList(Long userId, Long clubId);

    List<MasterClubMemberListResponse> findClubMemberListByMaster(Long userId, Long clubId);
}
