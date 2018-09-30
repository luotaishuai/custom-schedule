package schedule.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import schedule.common.DateUtil;
import schedule.common.RestResp;
import schedule.entity.ScheduleData;

import javax.annotation.Resource;
import java.util.concurrent.ScheduledFuture;

/**
 * @author anonymity
 * @create 2018-09-30 10:04
 **/
@Slf4j
@Service
public class ScheduleService {
    @Resource
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;


    @Resource
    private Test1Runnable test1Runnable;
    @Resource
    private Test2Runnable test2Runnable;

    /**
     * 在ScheduledFuture中有一个cancel可以停止定时任务。
     */
    private final String[] thread_name = new String[]{"test1", "test2"};

    private ScheduledFuture<?> test1;
    private ScheduledFuture<?> test2;

    /**
     * ThreadPoolTaskScheduler：线程池任务调度类，能够开启线程池进行任务调度。
     * ThreadPoolTaskScheduler.schedule()方法会创建一个定时计划ScheduledFuture，在这个方法需要添加两个参数，Runnable（线程接口类） 和CronTrigger（定时任务触发器）
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    public RestResp startJob(ScheduleData data) {
        String time = DateUtil.getPresentDate();
        if (thread_name[0].equals(data.getThreadName())) {
            test1 = threadPoolTaskScheduler.schedule(test1Runnable, new CronTrigger(data.getCron()));
            log.info("test1线程开始工作: cron = {}, start time: {}", data.getCron(), time);
        } else if (thread_name[1].equals(data.getThreadName())) {
            test2 = threadPoolTaskScheduler.schedule(test2Runnable, new CronTrigger(data.getCron()));
            log.info("Start BLOCK data: cron = {}, start time: {}", data.getCron(), time);
        } else {
            return RestResp.fail();
        }
        return RestResp.success();
    }

    public RestResp stopJob(ScheduleData data) {
        String time = DateUtil.getPresentDate();
        if (thread_name[0].equals(data.getThreadName())) {
            if (null != test1) {
                test1.cancel(true);
            }
            log.info("test1线程停止: stop time: {}", data.getCron(), time);
        } else if (thread_name[1].equals(data.getThreadName())) {
            if (null != test2) {
                test2.cancel(true);
            }
            log.info("test2线程停止: stop time: {}", data.getCron(), time);
        } else {
            return RestResp.fail("木有这个线程");
        }
        return RestResp.success();
    }
}
