package jpj.boot.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/2
 */
public class RegexVal extends EmptyVal implements Validator{
    private String regex;
    public RegexVal(String regex){
        this.regex = regex;
    }
    @Override
    public boolean validator(Object val){
        if(!super.validator(val))
            return false;
        return ((String)val).matches(regex);
    }
}
