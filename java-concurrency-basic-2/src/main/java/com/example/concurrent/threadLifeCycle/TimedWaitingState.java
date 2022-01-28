package com.example.concurrent.threadLifeCycle;

/*
    Create by Atiye Mousavi 
    Date: 1/24/2022
    Time: 4:39 PM
**/
public class TimedWaitingState {
//    //    یک رشته زمانی در حالت TIMED_WAITING است که منتظر رشته دیگری برای انجام یک عمل خاص در مدت زمان مشخص است.
////
////طبق JavaDocs، پنج راه برای قرار دادن رشته در وضعیت TIMED_WAITING وجود دارد:
////
////thread.sleep(long millis)
////wait(int timeout) or wait(int timeout, int nanos)
////thread.join(long millis)
////LockSupport.parkNanos
////LockSupport.parkUntil
    public static void main(String[] args) throws InterruptedException {
        DemoThread obj1=new DemoThread();
        Thread t1=new Thread(obj1);
        t1.start();
        Thread.sleep(1000);
        System.out.println(t1.getState());//print TIMED_WAITING
    }

}
class DemoThread implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
