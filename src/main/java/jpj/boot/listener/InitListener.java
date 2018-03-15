package jpj.boot.listener;

import com.alibaba.fastjson.JSON;
import jpj.boot.cache.UserCache;
import jpj.boot.entity.User;
import jpj.boot.service.UserService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 启动初始化数据测试
 * @Author: jingpj
 * @Date：creste in 2018/3/8
 */
@Component
@Log4j2
public class InitListener implements InitializingBean, ApplicationContextAware {
    @Resource
    private UserService userService;

    @Override
    public void afterPropertiesSet() throws Exception {
        //applicationContext.getBean()
        log.info("初始化增加添加缓存");
        List<User> users= userService.listAllSaleUser();
        UserCache.CACHE_SALES = users.stream().collect(Collectors.toSet());
    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
