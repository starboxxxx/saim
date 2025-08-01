package moobean.saim.server.global.security;

public class WebSecurityUrl {

    private WebSecurityUrl() {
        throw new IllegalStateException("Utility class");
    }

    public static final String [] HEALTH_CHECK_ENDPOINT = {"/health"};
    public static final String[] READ_ONLY_PUBLIC_ENDPOINTS = {"/favicon.ico"};

}
