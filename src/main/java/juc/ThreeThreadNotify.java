package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by renjin on 2019/3/4.
 */
public class ThreeThreadNotify {
    private static Lock lock=new ReentrantLock();
    private static Condition condition1=lock.newCondition();
    private static Condition condition2=lock.newCondition();
    private static Condition condition3=lock.newCondition();

    private static int x=1;

    public static void main(String[] args) {
//        ExecutorService executorService=Executors.newFixedThreadPool(3);
//        executorService.execute(new Thread1());
//        executorService.execute(new Thread2());
//        executorService.execute(new Thread3());
        System.out.println("sssssss");
        System.out.println((0-1)/2);
    }

     static  class Thread1 implements  Runnable{

        @Override
        public void run() {
            int i=0;
            while (i<10) {
                try {
                    lock.lock();
                    if (x == 1) {
                        System.out.println("A");
                        x=2;
                        i++;
                        condition2.signal();
                    }
                    condition1.await();
                } catch (Exception r) {
                    r.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class Thread2 implements  Runnable{

        @Override
        public void run() {
            int i=0;
            while (i<10) {
                try {
                    lock.lock();
                    if (x == 2) {
                        System.out.println("B");
                        x=3;
                        i++;
                        condition3.signal();
                    }
                    condition2.await();
                } catch (Exception r) {
                    r.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class Thread3 implements  Runnable{

        @Override
        public void run(){
            int i=0;
            while (i<10) {
                try {
                    lock.lock();
                    if (x == 3) {
                        System.out.println("C");
                        x=1;
                        i++;
                        condition1.signal();
                    }
                    condition3.await();
                } catch (Exception r) {
                    r.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
