package jpj.boot.dto;

import jpj.boot.annotation.IsRegex;
import jpj.boot.annotation.NotEmpty;
import lombok.Data;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/1
 */
@Data
public class UserResetPasswordDto {
    @NotEmpty("用户名不能为空！")
    private String userName;
    @NotEmpty("原密码不能为空！")
    private String oldPassword;
    @NotEmpty("新密码不能为空！")
    @IsRegex(value = "[0-9a-zA-Z]{6,}",message = "密码只能是数字或者字母且长度要大于6位")
    private String newPassword;

    private String errorMsg;
}
