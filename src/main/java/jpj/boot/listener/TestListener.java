package jpj.boot.listener;

import jpj.boot.listener.event.TestEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: jingpj
 * @Date：creste in 2018/2/27
 */
//@Component
public class TestListener implements ApplicationListener<TestEvent> {
    @Override
    public void onApplicationEvent(TestEvent testEvent) {
        System.out.println("TestListener" + testEvent.getSource().getName());
    }
}
