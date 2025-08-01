package moobean.saim.server.oauth.domain;

import java.time.LocalDate;

public interface OAuthUserInfo {
    String getId();
    String getName();
    String getEmail();
    String getPhone();
    LocalDate getBirth();
    String getProvider();
}
