package moobean.saim.server.global.annoation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier("accessTokenStrategy")
public @interface AccessTokenStrategy {
}
