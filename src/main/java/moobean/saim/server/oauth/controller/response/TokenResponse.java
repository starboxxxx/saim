package moobean.saim.server.oauth.controller.response;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
