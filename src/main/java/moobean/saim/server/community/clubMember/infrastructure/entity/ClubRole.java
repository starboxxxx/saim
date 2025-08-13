package moobean.saim.server.community.clubMember.infrastructure.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClubRole {

    MASTER("클럽 마스터"),
    GENERAL("일반 회원")
    ;

    private final String description;
}
