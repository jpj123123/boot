package jpj.boot.annotation;

import java.lang.annotation.*;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/1
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotEmpty {
    String value()default "";
}
