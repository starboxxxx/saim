package moobean.saim.server.oauth.domain;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

public class KakaoUserInfo implements OAuthUserInfo {

    private Map<String, Object> attributes;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getName() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");
        return (String) profile.get("nickname");
    }

    @Override
    public String getEmail() {
        return "leegd120@gmail.com";
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
        return "kakao";
    }
}
