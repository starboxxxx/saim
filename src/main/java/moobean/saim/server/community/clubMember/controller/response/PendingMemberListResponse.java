package moobean.saim.server.community.clubMember.controller.response;

import java.time.LocalDate;

public record PendingMemberListResponse(
        Long userId,
        String name,
        LocalDate applyDate
) {
}
