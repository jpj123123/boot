package jpj.boot.util;

import jpj.boot.annotation.IsRegex;
import jpj.boot.annotation.NotEmpty;
import jpj.boot.dto.UserLoginDto;
import jpj.boot.exception.BuisnessException;
import jpj.boot.validator.EmptyVal;
import jpj.boot.validator.RegexVal;
import jpj.boot.validator.ValidatorFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/1
 */
public class BeanValidator {
    public static Boolean validator(Object obj) {
        if (obj == null) return true;

        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            NotEmpty ann = field.getAnnotation(NotEmpty.class);
            Object val = null;
            try {
                val = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (ann != null) {
                String code = ann.value();
                if (StringUtils.isEmpty(code)) {
                    code = field.getName() + "NOT NULL";
                }
                if(!ValidatorFactory.getValidator(new EmptyVal()).validator(val)){
                    throw new BuisnessException(code);
                };

            }
        }
        return true;
    }
    public static boolean validator(Object obj, ModelAndView mav) {
        if (obj == null) return true;

        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            NotEmpty ann = field.getAnnotation(NotEmpty.class);
            IsRegex reg = field.getAnnotation(IsRegex.class);
            Object val = null;
            try {
                val = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (ann != null) {

                String code = ann.value();
                if (StringUtils.isEmpty(code)) {
                    code = field.getName() + "NOT NULL";
                }
                if(!ValidatorFactory.getValidator(new EmptyVal()).validator(val)){
                    mav.addObject("errorMsg", code);
                    return false;
                };

            }
            if (reg != null) {
                String code = reg.message();
                String regex = reg.value();
                if (StringUtils.isEmpty(code)) {
                    code = field.getName() + "数据规则不对";
                }
                if(!ValidatorFactory.getValidator(new RegexVal(regex)).validator(val)){
                    mav.addObject("errorMsg", code);
                    return false;
                };
            }
        }
        return true;
    }
    public static void main(String[] args) throws IllegalAccessException {
        UserLoginDto dto = new UserLoginDto();
        dto.setUserName("ssss");
        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            System.out.println(f.get(dto));
        }
    }
}
