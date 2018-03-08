package jpj.boot.application;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 添加配置文件并加入spring容器中
 * @Author: jingpj
 * @Date：creste in 2018/2/8
 */
@Data
@Component
@ConfigurationProperties(prefix = "ignore")
public class ApplicatoinConfig {
    private List<String> ignoreLoginUrlList;
}
