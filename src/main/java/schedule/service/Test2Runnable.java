package schedule.service;

import org.springframework.stereotype.Component;

/**
 * @author anonymity
 * @create 2018-09-30 10:13
 **/
@Component
public class Test2Runnable implements Runnable {
    @Override
    public void run() {
        test2();
    }

    private void test2() {
        System.err.println("谁最帅，我最帅");
    }
}
