package moobean.saim.server.global.annoation;


import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier("refreshTokenStrategy")
public @interface RefreshTokenStrategy {
}
