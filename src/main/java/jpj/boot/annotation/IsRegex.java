package jpj.boot.annotation;

import java.lang.annotation.*;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/2
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IsRegex {
    String value();
    String message()default "";
}
