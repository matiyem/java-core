package com.example.concurrent.threads.name;

/*
    Create by Atiye Mousavi 
    Date: 1/25/2022
    Time: 10:38 AM
**/
public class CustomThreadName {

    public int currentNumber = 1;

    public int N = 5;

    public static void main(String[] args) {
        CustomThreadName test = new CustomThreadName();

        Thread oddThread = new Thread(() -> {
            test.printOddNumber();
        },"ODD");

        Thread evenThread=new Thread(()->{
            test.printEvenNumber();
        },"EVEN");

        evenThread.start();
        oddThread.start();
    }

    public void printEvenNumber() {
        synchronized (this) {
            while (currentNumber < N) {
                while (currentNumber % 2 == 1) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " --> " + currentNumber);
                currentNumber++;
//                متد wait() باعث می‌شود تا رشته فعلی به‌طور نامحدود منتظر بماند تا رشته‌ای دیگر یا notify() را برای این شیء فراخوانی کند یا notifyAll().
//
                notify();
            }
        }
    }
    public void printOddNumber() {
        synchronized (this) {
            while (currentNumber < N) {
                while (currentNumber % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " --> " + currentNumber);
                currentNumber++;
                notify();
            }
        }
    }
}
