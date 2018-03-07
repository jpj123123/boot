package jpj.boot.dto;

import jpj.boot.annotation.NotEmpty;
import lombok.Data;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/4
 */
@Data
public class SubmitEnumDto {
    private Integer id;
    @NotEmpty("上级id不能为空")
    private Integer pid;
    @NotEmpty("菜单名不能为空")
    private String name;
    @NotEmpty("菜单编号不能为空")
    private String code;
    private String icons;
    @NotEmpty("链接不能为空")
    private String url;
    @NotEmpty("类型不能为空")
    private Byte type;
    private Integer desc;
}
