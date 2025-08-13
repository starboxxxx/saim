package moobean.saim.server.community.club.controller.response;

public record ClubListResponse(
        Long clubId,
        String clubName,
        Long memberCount,
        Long postCount
) {
}
