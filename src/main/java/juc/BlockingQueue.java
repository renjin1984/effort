package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by renjin on 2019/3/5.
 */
public class BlockingQueue {
    private int[] arrays;
    private int max;

    private Lock putLock;
    private Condition notFull;

    private Lock takeLock;
    private Condition notEmpty;

    public  BlockingQueue(int blockingQueueSize){
        this.max=blockingQueueSize;
        this.arrays=new int[blockingQueueSize];
        putLock=new ReentrantLock();
        notFull=putLock.newCondition();
        takeLock=new ReentrantLock();
        notEmpty=takeLock.newCondition();
    }

    public void put(int x){
        putLock.lock();
        try{
            if (arrays.length<max){
                arrays[arrays.length]=x;
                notFull.signal();
            }
            else{
                notFull.await();
            }
        }
        catch (Exception e){

        }
        finally {
            putLock.unlock();
        }

    }


}
