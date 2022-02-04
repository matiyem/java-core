package com.example.concurrent.locks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/*
    Create by Atiye Mousavi 
    Date: 1/31/2022
    Time: 10:55 AM
**/
public class ReentrantLockWithCondition {
//    کلاس ReentrantLock رابط Lock را پیاده سازی می کند. این همان معنای همزمانی و حافظه را ارائه می‌دهد، همانطور که قفل مانیتور ضمنی با استفاده از روش‌ها و عبارات همگام‌سازی شده با قابلیت‌های گسترده قابل دسترسی است.
//
//بیایید ببینیم چگونه می توانیم از ReenrtantLock برای همگام سازی استفاده کنیم:
//    کلاس Condition این امکان را فراهم می کند که یک رشته در حین اجرای بخش بحرانی منتظر رخ دادن شرایط باشد.
//
//این می تواند زمانی رخ دهد که یک رشته به بخش بحرانی دسترسی پیدا کند اما شرایط لازم برای انجام عملیات خود را نداشته باشد. به عنوان مثال، یک رشته خواننده می تواند به قفل یک صف مشترک دسترسی پیدا کند، که هنوز هیچ داده ای برای مصرف ندارد.
//
//به طور سنتی جاوا متدهای wait()، notify() و notifyAll() را برای ارتباط درونی رشته ارائه می کند. شرایط مکانیسم‌های مشابهی دارند، اما علاوه بر این، می‌توانیم شرایط متعددی را مشخص کنیم:

    private static Logger LOG = LoggerFactory.getLogger(ReentrantLockWithCondition.class);

    private Stack<String> stack = new Stack<>();
    private static final int CAPACITY = 5;

    private ReentrantLock lock = new ReentrantLock();
    private Condition stackEmptyCondition = lock.newCondition();
    private Condition stackFullCondition = lock.newCondition();

    private void pushToStack(String item) throws InterruptedException {
        lock.lock();
        try {
            if (stack.size() == CAPACITY) {
                LOG.info(Thread.currentThread().getName() + " wait on stack full");
                stackEmptyCondition.await();
            }
            LOG.info("Pushing the item " + item);
            stack.push(item);
            stackEmptyCondition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    private String popFromStack() throws InterruptedException {
        try {
            lock.lock();
            if (stack.size()==0){
                LOG.info(Thread.currentThread().getName()+" wait on stack empty");
                stackEmptyCondition.await();
            }
            return stack.pop();
        }finally {
            stackEmptyCondition.signalAll();
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final int threadCount=2;
        ReentrantLockWithCondition object=new ReentrantLockWithCondition();
        final ExecutorService service= Executors.newFixedThreadPool(threadCount);
        service.execute(()->{
            for (int i=0 ; i<10; i++){
                try {
                    object.pushToStack("Item " + i);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        service.execute(()->{
            for (int i=0 ; i<10 ; i++){
                try {
                    LOG.info("Item popped " + object.popFromStack());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        service.shutdown();

    }
}
