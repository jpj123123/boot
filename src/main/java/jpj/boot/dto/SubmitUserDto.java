package jpj.boot.dto;

import jpj.boot.annotation.IsRegex;
import jpj.boot.annotation.NotEmpty;
import lombok.Data;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/6
 */
@Data
public class SubmitUserDto {
    private Long id;
    @NotEmpty("上级id不能为空")
    private Long pid;
    @NotEmpty("用户名不能为空")
    @IsRegex(value = "[0-9a-zA-Z_]{3,}",message = "用户名只能是数字、字母、_且长度要大于3位")
    private String name;

    @NotEmpty("新密码不能为空！")
    @IsRegex(value = "[0-9a-zA-Z]{6,}",message = "密码只能是数字或者字母且长度要大于6位")
    private String password;

    private boolean issale;
}
