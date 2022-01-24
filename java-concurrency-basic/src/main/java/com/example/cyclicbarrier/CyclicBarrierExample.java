package com.example.cyclicbarrier;



import java.util.concurrent.CyclicBarrier;

/*
    Create by Atiye Mousavi 
    Date: 1/17/2022
    Time: 11:22 AM
**/
public class CyclicBarrierExample {

    public void start(){
        CyclicBarrier cyclicBarrier=new CyclicBarrier(3,()->{
            System.out.println("All previous tasks are completed");
        });

        Thread t1=new Thread(new Task(cyclicBarrier),"T1");
        Thread t2=new Thread(new Task(cyclicBarrier),"T2");
        Thread t3=new Thread(new Task(cyclicBarrier),"T3");

        if (!cyclicBarrier.isBroken()){
            t1.start();
            t2.start();
            t3.start();
        }
    }
}
