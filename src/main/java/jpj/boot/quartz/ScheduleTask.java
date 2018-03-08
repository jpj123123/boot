package jpj.boot.quartz;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * spring自带定时器测试
 * @Author: jingpj
 * @Date：creste in 2018/3/8
 */
//@EnableScheduling
@Component
public class ScheduleTask {
    @Scheduled(cron="0/15 * * * * ?")
    public void scheduleTest() {
        System.err.println("scheduleTest开始定时执行");
    }
}
