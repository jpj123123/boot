package jpj.boot.exception.exceptionhandler;

import jpj.boot.converter.Result;
import jpj.boot.exception.BuisnessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/5
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(BuisnessException.class)
    public Object handleControllerException(HttpServletRequest request, Throwable ex) {
        Result result = new Result();
        result.setMessage("error");
        result.setCode("-1");
        //String jsonStr = JSON.toJSONString(obj, serializerFeatures);
        result.setBody(ex.getMessage());
        return result;
    }
}
