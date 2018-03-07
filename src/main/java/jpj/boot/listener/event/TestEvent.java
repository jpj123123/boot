package jpj.boot.listener.event;

import jpj.boot.domain.Customer;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: jingpj
 * @Date：creste in 2018/2/27
 */
public class TestEvent extends ApplicationEvent {
    private Customer source;
    public TestEvent(Customer source) {
        super(source);
        this.source=source;
    }
    public Customer getSource(){
        return source;
    }
}
