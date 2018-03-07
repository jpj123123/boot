package jpj.boot.service.impl;

import jpj.boot.domain.Customer;
import jpj.boot.listener.event.TestEvent;
import jpj.boot.service.TestLintenerService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @Author: jingpj
 * @Date：creste in 2018/2/27
 */
@Service
public class TestLintenerServiceImpl implements TestLintenerService, ApplicationContextAware {

    @Override
    public void doSome(String name) {
        System.out.println(applicationContext);
        Assert.notNull(applicationContext, "注入失败！！");
        Customer customer = new Customer();
        customer.setName(name);
        System.out.println("做一部分事情");

        TestEvent testEvent = new TestEvent(customer);
        applicationContext.publishEvent(testEvent);
    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
