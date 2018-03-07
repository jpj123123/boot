package jpj.boot.dto;

import jpj.boot.annotation.NotEmpty;
import lombok.Data;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/1
 */
@Data
public class UserLoginDto {
    @NotEmpty("用户名不能为空！")
    private String userName;
    private String password;
    private String veliCode;
}
