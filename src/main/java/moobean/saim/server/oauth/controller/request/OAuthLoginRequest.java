package moobean.saim.server.oauth.controller.request;

public record OAuthLoginRequest(
        String provider,
        String code
) {
}
