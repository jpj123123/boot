package jpj.boot.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

/**
 * @Author: jingpj
 * @Date：creste in 2018/2/8
 */
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@ComponentScan({"jpj.boot"})
@MapperScan("jpj.boot.dao")
public class Application {

    public static void main(String[] args) throws Exception {
//        Properties prop = new Properties();
//        prop.load(Application.class.getClassLoader().getResourceAsStream("config/test.yml"));
//        new SpringApplication().setDefaultProperties(prop);
        SpringApplication.run(Application.class, args).start();
    }
}
