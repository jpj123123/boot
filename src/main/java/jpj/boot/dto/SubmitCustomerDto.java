package jpj.boot.dto;

import jpj.boot.annotation.NotEmpty;
import lombok.Data;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/7
 */
@Data
public class SubmitCustomerDto {
    private Long id;
    @NotEmpty("客户名不能为空")
    private String name;
    @NotEmpty("客户电话不能为空")
    private String phone;
    @NotEmpty("客户地址不能为空")
    private String address;
    @NotEmpty("客户余额不能为空")
    private Long money;
    private Long userId;
}
