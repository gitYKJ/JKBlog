package com.jkblog.util;

import java.util.concurrent.LinkedBlockingQueue;

public class StrogeQueue {
    /*
    *生产者和消费者模式
    * 当生产者为空时消费者线程停止消费
    * 当消费者为空时生产者线程开始生产
    * 保证线程安全
    * 三种实现1、wait/notfiy2、锁实现lock3、阻塞队列，信号量
    */
    LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);


}
