package com.geek.base.concurrent.FutureTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author G.E.E.K.
 * @create 2022-06-30 21:31
 */
public class FutureTaskExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            int result = 0;
            for (int i = 0; i < 100; i++) {
                Thread.sleep(100);
                result += i;
            }
            return result;
        });

        Thread computeThread = new Thread(futureTask);
        computeThread.start();

        Thread otherThread = new Thread(() -> {
            System.out.println("other task is running...");
            try {
                Thread.sleep(1000);
                System.out.println("other task is finished...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        otherThread.start();
        // 阻塞等待，异步获取结果
        System.out.println(futureTask.get());
    }
}
