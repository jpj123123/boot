package jpj.boot.converter;

import lombok.Data;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/5
 */
@Data
public class Result {
    private String code;
    private String message;
    private Object body;
}
