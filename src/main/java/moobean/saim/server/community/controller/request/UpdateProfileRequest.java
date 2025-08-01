package moobean.saim.server.community.controller.request;

public record UpdateProfileRequest(
        String name,
        String email,
        String introduce
) {
}
