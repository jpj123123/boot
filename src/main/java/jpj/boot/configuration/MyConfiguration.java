package jpj.boot.configuration;

import com.alibaba.fastjson.JSON;
import jpj.boot.converter.MyHttpConverter;
import jpj.boot.converter.StringHttpConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/1
 */
@Configuration
//@ConditionalOnClass({Object.class})
public class MyConfiguration {

    @Bean
    public HttpMessageConverters customConverters() {
        HttpMessageConverter<?> additional = new MyHttpConverter();
        HttpMessageConverter<String> strConnerter = new StringHttpConverter();
        return new HttpMessageConverters(additional);
    }

}