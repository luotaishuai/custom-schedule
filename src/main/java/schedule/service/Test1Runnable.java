package schedule.service;

import org.springframework.stereotype.Component;

/**
 * @author anonymity
 * @create 2018-09-30 10:13
 **/
@Component
public class Test1Runnable implements Runnable {
    @Override
    public void run() {
        test1();
    }

    private void test1() {
        System.err.println("中彩票金额：" + Math.random());
    }
}
