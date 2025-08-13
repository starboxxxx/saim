package moobean.saim.server.community.clubMember.controller.request;

public record CreateClubMemberRequest(
        Long userId,
        Long clubId
) {
}
