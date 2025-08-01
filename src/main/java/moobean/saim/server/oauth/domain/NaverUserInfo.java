package moobean.saim.server.oauth.domain;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class NaverUserInfo implements OAuthUserInfo{

    private Map<String, Object> attributes;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getId() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return String.valueOf(response.get("id"));
    }

    @Override
    public String getName() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return (String) response.get("name");
    }

    @Override
    public String getEmail() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return (String) response.get("email");
    }

    @Override
    public String getPhone() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return (String) response.get("mobile");
    }

    @Override
    public LocalDate getBirth() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        String year = (String) response.get("birthyear");
        String month = (String) response.get("birthday");

        return parseBirth(year, month);
    }

    @Override
    public String getProvider() {
        return "naver";
    }


    private LocalDate parseBirth(String birthYear, String birthday) {
        try {
            String fullDate = birthYear + "-" + birthday;
            return LocalDate.parse(fullDate);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
