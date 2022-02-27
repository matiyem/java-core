package com.example.deadLockAndLiveLock;

/*
    created by Atiye Mousavi
    Date: 2/15/2022
    Time: 4:35 PM
*/


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {
    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);
//در کد بالا به وضوح می بینیم که ابتدا threadA lock1 را بدست می آورد و threadB lock2 را بدست می آورد. سپس، threadA سعی می کند lock2 را که قبلاً توسط threadB به دست آمده است و threadB سعی می کند lock1 را که قبلاً توسط threadA به دست آورده است را بدست آورد. بنابراین، هیچ یک از آنها به این معنی که آنها در بن بست هستند، ادامه نمی دهند.
//
//با تغییر ترتیب قفل ها در یکی از نخ ها به راحتی می توانیم این مشکل را برطرف کنیم.
//
//باید توجه داشته باشیم که این تنها یک مثال است و موارد بسیار دیگری نیز وجود دارند که می توانند به بن بست منجر شوند.
    public static void main(String[] args) {
        DeadlockExample deadlock=new DeadlockExample();
        new Thread(deadlock::operation1,"T1").start();
        new Thread(deadlock::operation2,"T2").start();

    }

    public void operation1() {
        lock1.lock();
        print("lock1 acquired, waiting to acquire lock2.");
        sleep(50);

        lock2.lock();
        print("lock2 acquired");
        print("executing first operation.");

        lock2.unlock();
        lock1.unlock();

    }
    public void operation2(){
        lock2.lock();
        print("lock2 acquired, waiting to acquire lock1");
        sleep(50);

        lock1.lock();
        print("lock1 acquired");
        print("executing second operation.");

        lock1.unlock();
        lock2.unlock();
    }

    public void print(String message) {
        System.out.println("Thread " + Thread.currentThread().getName() + ": " + message);
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
