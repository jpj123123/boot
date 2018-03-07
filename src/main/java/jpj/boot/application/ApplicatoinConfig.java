package jpj.boot.application;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/2/8
 */
@Data
//@Component
//@ConfigurationProperties(prefix = "my")
public class ApplicatoinConfig {

    private List<String> test;
}
