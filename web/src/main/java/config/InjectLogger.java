package config;

import javax.inject.Scope;
import java.lang.annotation.*;
@Scope
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectLogger {
}