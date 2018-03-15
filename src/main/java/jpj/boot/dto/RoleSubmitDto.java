package jpj.boot.dto;

import jpj.boot.annotation.NotEmpty;
import lombok.Data;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/6
 */
@Data
public class RoleSubmitDto {
    @NotEmpty("上级id不能为空")
    private Long pid;
    @NotEmpty("角色名不能为空")
    private String name;
}
