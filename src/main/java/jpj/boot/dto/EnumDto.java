package jpj.boot.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/2
 */
@Data
public class EnumDto {
    private Integer pid;
    private String id;
    private String text;
    private String state;
    private Map<String,Object> attributes;
    private String url;
    private Byte type;
    private Integer desc;
    private List<EnumDto> children;
}
