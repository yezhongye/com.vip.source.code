package com.study.design.mode.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 功能描述: <br>
 *
 * @author zjx
 * @version 1.0.0
 * @date 2021/5/8 16:21
 */
public class ThreadTimeOutTest {

    @Test
    public void myThreadTimeOutTest(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> callable = () -> {
            System.out.println("任务执行中");
            Thread.sleep(1000 * 10);
            return "SUCCESS";
        };
        Future<String> futureResult = executorService.submit(callable);
        String resultStr = "初始";
        try {
            resultStr = futureResult.get(11,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            resultStr = "任务超时";
        }
        executorService.shutdown();
        System.out.println("执行结果："+ resultStr);
    }
}
