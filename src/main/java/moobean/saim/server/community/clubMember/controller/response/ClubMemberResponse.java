package moobean.saim.server.community.clubMember.controller.response;

public record ClubMemberResponse(
        Long userId,
        String userName,
        Boolean isSame,
        Boolean isFollowed
) {
}
