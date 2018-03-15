package jpj.boot.dto;

import jpj.boot.annotation.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/6
 */
@Data
public class AddRoleEnumSubmitDto {
    @NotEmpty("缺少角色Id")
    private Long roleId;
    private List<Long> enums;
}
