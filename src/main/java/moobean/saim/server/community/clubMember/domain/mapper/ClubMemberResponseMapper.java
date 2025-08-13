package moobean.saim.server.community.clubMember.domain.mapper;

import moobean.saim.server.community.clubMember.controller.response.*;
import moobean.saim.server.community.clubMember.domain.ClubMember;
import moobean.saim.server.user.domain.User;

import java.util.List;

public class ClubMemberResponseMapper {

    public static ClubMemberResponse toClubMemberResponse(ClubMember member,
                                                          Boolean isSame, Boolean isFollowed) {
        return new ClubMemberResponse(
                member.getUser().getId(),
                member.getUser().getName(),
                isSame,
                isFollowed
        );
    }

    public static InviteMemberListResponse toInviteMemberListResponse(User user) {
        return new InviteMemberListResponse(
                user.getId(),
                user.getName()
        );
    }

    public static MasterClubMemberListResponse toMasterClubMemberListResponse(ClubMember member) {
        return new MasterClubMemberListResponse(
                member.getUser().getId(),
                member.getUser().getName(),
                member.getUser().getEmail(),
                member.getClubRole()
        );
    }

    public static PendingMemberListResponse toPendingMemberListResponse(ClubMember clubMember) {
        return new PendingMemberListResponse(
                clubMember.getUser().getId(),
                clubMember.getUser().getName(),
                clubMember.getCreatedTime().toLocalDate()
        );
    }

    public static ClubMemberListResponse toClubMemberListResponse(Boolean secretClub,
                                                                  List<ClubMemberResponse> clubMembers) {
        return new ClubMemberListResponse(
                secretClub,
                clubMembers
        );
    }

    public static ClubMemberListResponse toSecretClubMemberListResponse() {
        return new ClubMemberListResponse(
                true,
                null
        );
    }
}
