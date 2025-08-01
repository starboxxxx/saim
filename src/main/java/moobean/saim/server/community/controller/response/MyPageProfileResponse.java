package moobean.saim.server.community.controller.response;

public record MyPageProfileResponse(
        Long userId,
        String name,
        String email,
        String introduce
) {
}
