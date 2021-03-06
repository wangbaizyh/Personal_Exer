package com.geek.base.concurrent.ForkJoin;

import java.util.concurrent.*;

/**
 * @author G.E.E.K.
 * @create 2022-06-30 21:48
 *
 * 类似于Map Reduce的执行过程
 *
 */
public class ForkJoinExample extends RecursiveTask<Integer> {

    private static final int threshold = 5;
    private final int first;
    private final int last;

    public ForkJoinExample(int first, int last) {
        this.first = first;
        this.last = last;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (last - first <= threshold) {
            // 任务足够小则直接计算
            for (int i = first; i <= last; i++) {
                result += i;
            }
        } else {
            // 拆分成小任务
            int middle = first + (last - first) / 2;
            ForkJoinExample leftTask = new ForkJoinExample(first, middle);
            ForkJoinExample rightTask = new ForkJoinExample(middle + 1, last);
            leftTask.fork();
            rightTask.fork();
            result = leftTask.join() + rightTask.join();
        }
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinExample example = new ForkJoinExample(1, 10000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> result = forkJoinPool.submit(example);
        System.out.println(result.get());
    }
}
