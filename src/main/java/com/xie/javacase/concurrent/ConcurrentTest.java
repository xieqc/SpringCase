package com.xie.javacase.concurrent;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: Xie
 * Date: 14-9-25
 * Time: 上午10:49
 * To change this template use File | Settings | File Templates.
 */
public class ConcurrentTest {
    private ExecutorService executorService;    //线程池
    private final int POOL_SIZE = 10;   //单个CPU线程池大小

    ConcurrentHashMap map = new ConcurrentHashMap();
    ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();  //一个基于链接节点的无界线程安全队列。此队列按照 FIFO（先进先出）原则对元素进行排序。

    @Test
    public void ConcurrentHashMapTest() {
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE); //Runtime的availableProcessor()方法返回当前系统的CPU数目.
        Handler handler1 = new Handler(queue,"a");
        executorService.execute(handler1);
        Handler handler2 = new Handler(queue,"b");
        executorService.execute(handler2);

        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while(!queue.isEmpty());

    }

}

class Handler implements Runnable {
    ConcurrentLinkedQueue<String> queue;
    String flag;

    Handler(ConcurrentLinkedQueue queue, String flag) {
        this.queue = queue;
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            for(int i=0;i<100;i++) {
                queue.offer(flag+i);
                Thread.sleep(100);
            }
            while(!queue.isEmpty()) {   //由于这些队列的异步特性，确定当前元素的数量需要遍历这些元素。size()非常耗时这里由isEmpty()进行判断
                System.out.println(queue.poll());
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}