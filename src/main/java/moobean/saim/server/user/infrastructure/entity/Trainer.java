package moobean.saim.server.user.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.ProfileErrorCode;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Trainer {
    MICHAEL("마이클"),
    SOFIA("소피아");

    private final String description;

    @JsonCreator
    public static Trainer from(String value) {
        return Arrays.stream(values())
                .filter(t -> t.name().equalsIgnoreCase(value) || t.description.equals(value))
                .findFirst()
                .orElseThrow(() -> new ApplicationException(ProfileErrorCode.TRAINER_INVALID));
    }
}
