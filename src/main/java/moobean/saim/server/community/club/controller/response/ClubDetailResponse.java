package moobean.saim.server.community.club.controller.response;

import java.time.LocalDateTime;

public record ClubDetailResponse(
        Boolean isMember,
        String name,
        String introduce,
        Long memberCount,
        Long postCount,
        LocalDateTime createdTime
) {
}
