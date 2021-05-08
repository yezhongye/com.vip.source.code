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
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000 * 10);
                return "SUCCESS";
            }
        };
        Future<String> futureResult = executorService.submit(callable);
        String resultStr;
        try {
            resultStr = futureResult.get(7,TimeUnit.SECONDS);
            System.out.println("任务返回结果："+resultStr);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("任务超时：");
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
