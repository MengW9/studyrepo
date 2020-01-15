package com.thread;

/**
 * @program: algorithm_demo
 * @description: 交替获取锁的方法
 * @author: mengw9
 * @create: 2019-09-24 18:28
 **/
public class PrintOddEvenTwo {

    private int count = 0;
    private final Object lock = new Object();

    public void turning() throws InterruptedException {
        Thread even = new Thread(() -> {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println("偶数: " + count++);
                    lock.notifyAll();
                    try {
                        // 如果还没有结束，则让出当前的锁并休眠
                        if (count <= 100) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread odd = new Thread(() -> {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println("奇数: " + count++);
                    lock.notifyAll();
                    try {
                        // 如果还没有结束，则让出当前的锁并休眠
                        if (count <= 100) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        even.start();
        // 确保偶数线程线先获取到锁
        Thread.sleep(1);
        odd.start();
    }

    public static void main(String[] args) throws InterruptedException {
        PrintOddEvenTwo printOddEvenTwo = new PrintOddEvenTwo();
//        printOddEvenTwo.turning();

        printOddEvenTwo.turningTwo();
    }

    public void turningTwo() throws InterruptedException {
        new Thread(new TurningRunner(), "偶数").start();
        // 确保偶数线程线先获取到锁
        Thread.sleep(1);
        new Thread(new TurningRunner(), "奇数").start();
    }

    class TurningRunner implements Runnable {
        @Override
        public void run() {
            while (count <= 100) {
                // 获取锁
                synchronized (lock) {
                    // 拿到锁就打印
                    System.out.println(Thread.currentThread().getName() + ": " + count++);
                    // 唤醒其他线程
                    lock.notifyAll();
                    try {
                        if (count <= 100) {
                            // 如果任务还没有结束，则让出当前的锁并休眠
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
