package jpj.boot.dto;

import jpj.boot.annotation.NotEmpty;
import lombok.Data;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/12
 */
@Data
public class SubmitGoodsDto {
    private Integer id;
    @NotEmpty("商品名字不能为空")
    private String name;
    @NotEmpty("商品编码不能为空")
    private String code;
    private Boolean islist;
    private Integer cost;//成本价
    private Integer price;
}
