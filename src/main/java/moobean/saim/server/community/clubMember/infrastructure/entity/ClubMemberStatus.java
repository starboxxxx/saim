package moobean.saim.server.community.clubMember.infrastructure.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClubMemberStatus {

    PENDING("승인대기 회원"),
    APPROVED("승인 회원");

    private final String description;
}
