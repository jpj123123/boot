package jpj.boot.dto;

import lombok.Data;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/15
 */
@Data
public class SubmitOutLibDto {
    private Boolean isOut;
    private Long userId;
    private Long goodsId;
    private Long goodsCount;
    private String remark;

}
