package jpj.boot.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/2
 */
public class EmptyVal implements Validator{
    public EmptyVal(){

    }
    @Override
    public boolean validator(Object val){
        if (Objects.isNull(val)) {
            return false;
        }
        if (val instanceof String) {
            if (StringUtils.isBlank((String) val)) {
                return false;
            }
        }
        return true;
    }
}
