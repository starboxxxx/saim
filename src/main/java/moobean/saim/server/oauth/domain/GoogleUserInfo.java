package moobean.saim.server.oauth.domain;

import com.nimbusds.openid.connect.sdk.claims.UserInfo;

import java.time.LocalDate;
import java.util.Map;

public class GoogleUserInfo implements OAuthUserInfo {

    private final Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getId() {
        return (String) attributes.get("id");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getPhone() {
        return "010-8844-4029";
    }

    @Override
    public LocalDate getBirth() {
        return LocalDate.of(2000, 1, 20); // 년, 월, 일
    }

    @Override
    public String getProvider() {
        return "google";
    }
}
