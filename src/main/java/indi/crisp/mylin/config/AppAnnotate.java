package indi.crisp.mylin.config;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface AppAnnotate {
    String value() ;
}
