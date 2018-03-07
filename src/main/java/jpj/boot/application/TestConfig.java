package jpj.boot.application;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @Author: jingpj
 * @Date：creste in 2018/2/9
 */
@Data
@ConfigurationProperties(prefix = "config.test")
public class TestConfig {
    private String testkey;
}
